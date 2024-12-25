package callLogs

import android.content.ContentResolver
import android.content.Context
import android.provider.CallLog
import callLogs.ContactName.Companion.DEFAULT_CONTACT_NAME
import com.mk.infrastructure.formatMsISO
import com.mk.infrastructure.models.UserContactData
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import javax.inject.Inject
import kotlin.collections.plus

interface ContactLoggerManager {
    val savedLaunchCallLogs: StateFlow<List<UserContactData>>
    fun initCallLogManager()
    fun syncCallLogs()
    fun registerCallLogs()
    fun unregisterCallLogs()
    fun getSavedLaunchCallLogs(): List<UserContactData>
}

/**
 * Implementation of [ContactLoggerManager] that manages and retrieves call log data.
 *
 * This class is responsible for:
 * - Observing changes to the call log.
 * - Querying call logs based on specified criteria.
 * - Storing and providing access to call log data.
 * - Tracking call logs since the app launch.
 *
 * @property context The application context.
 */
class ContactLoggerManagerImpl @Inject constructor(val context: Context) : ContactLoggerManager {
    val contentResolver = context.contentResolver
    private var lastQueryTime = System.currentTimeMillis().toString()
    private var appLaunchDate = System.currentTimeMillis().toString()
    private val _savedLaunchCallLogs = MutableStateFlow<List<UserContactData>>(listOf())
    override val savedLaunchCallLogs = _savedLaunchCallLogs.asStateFlow()

    private val callLogObserver = object : android.database.ContentObserver(null) {
        override fun onChange(selfChange: Boolean) {
            super.onChange(selfChange)
            syncCallLogs()
        }
    }

    override fun initCallLogManager() {
        queryCallLogs(contentResolver = contentResolver)
        registerCallLogs()
    }

    override fun registerCallLogs() {
        contentResolver.registerContentObserver(CallLog.Calls.CONTENT_URI, true, callLogObserver)
    }

    override fun unregisterCallLogs() {
        contentResolver.unregisterContentObserver(callLogObserver)
    }

    override fun getSavedLaunchCallLogs(): List<UserContactData> {
        _savedLaunchCallLogs.update {
            it.map { item -> item.copy(timesQueried = item.timesQueried + 1) }
        }
        return savedLaunchCallLogs.value
    }

    override fun syncCallLogs() {
        queryCallLogs(
            contentResolver = contentResolver,
            query = "${CallLog.Calls.DATE} >?",
            queryParams = arrayOf(lastQueryTime),
        )
    }

    private fun queryCallLogs(
        contentResolver: ContentResolver,
        query: String? = null,
        queryParams: Array<String>? = null,
    ) {
        val deviceUserName = getDeviceUserName(context)
        val appLaunchCallLogs = mutableListOf<UserContactData>()

        val callLogsBuffer = ArrayList<String>()
        callLogsBuffer.clear()

        val projections = arrayOf(
            CallLog.Calls.DURATION,
            CallLog.Calls.CACHED_NAME,
            CallLog.Calls.TYPE,
            CallLog.Calls.DATE,
            CallLog.Calls.CACHED_NORMALIZED_NUMBER,
            CallLog.Calls.NUMBER,
        )

        val cursor = contentResolver.query(
            CallLog.Calls.CONTENT_URI,
            projections,
            query,
            queryParams,
            CallLog.Calls.DATE + " DESC",
        )
        cursor?.let {
            if (cursor.count == 0) return

            val duration = cursor.getColumnIndex(CallLog.Calls.DURATION)
            val name = cursor.getColumnIndex(CallLog.Calls.CACHED_NAME)
            val type = cursor.getColumnIndex(CallLog.Calls.TYPE)
            val date = cursor.getColumnIndex(CallLog.Calls.DATE)
            val number = cursor.getColumnIndex(CallLog.Calls.NUMBER)
            val cachedNumber = cursor.getColumnIndex(CallLog.Calls.CACHED_NORMALIZED_NUMBER)

            cursor.moveToFirst()

            val callDate = cursor.getString(date)
            lastQueryTime = callDate

            do {
                val callDuration = cursor.getString(duration)
                val callType = cursor.getString(type)
                val phNumber = cursor.getString(cachedNumber) ?: cursor.getString(number)
                var dir: String? = null
                val dircode = Integer.parseInt(callType)
                val callerName = when (dircode) {
                    CallLog.Calls.OUTGOING_TYPE -> {
                        dir = "OUTGOING"
                        deviceUserName
                    }
                    CallLog.Calls.INCOMING_TYPE -> {
                        dir = "INCOMING"
                        cursor.getString(name) ?: DEFAULT_CONTACT_NAME
                    }
                    CallLog.Calls.MISSED_TYPE -> {
                        dir = "MISSED"
                        cursor.getString(name) ?: DEFAULT_CONTACT_NAME
                    }
                    CallLog.Calls.REJECTED_TYPE -> {
                        dir = "MISSED"
                        cursor.getString(name) ?: DEFAULT_CONTACT_NAME
                    }

                    else -> { deviceUserName }
                }

                if (callDate > appLaunchDate) {
                    appLaunchCallLogs.add(
                        UserContactData(
                            beginning = formatMsISO(callDate.toLong()),
                            duration = callDuration,
                            number = phNumber,
                            contactName = cursor.getString(name) ?: DEFAULT_CONTACT_NAME,
                            callerName = callerName,
                            timesQueried = 0,
                        ),
                    )
                }
            } while (cursor.moveToNext())
            cursor.close()
        }

        _savedLaunchCallLogs.update { appLaunchCallLogs + it }
    }
}
