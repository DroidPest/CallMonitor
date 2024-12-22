package callLogs

import android.content.Context
import android.net.Uri
import android.provider.ContactsContract
import android.provider.ContactsContract.PhoneLookup
import callLogs.ContactName.Companion.DEFAULT_CONTACT_NAME

class ContactName {
    companion object {
        const val DEFAULT_CONTACT_NAME = "Unknown"
    }
}

fun getContactName(context: Context, phoneNumber: String): String =
    getProfileName(context, Uri.withAppendedPath(PhoneLookup.CONTENT_FILTER_URI, Uri.encode(phoneNumber)))

fun getDeviceUserName(context: Context): String =
    getProfileName(context, ContactsContract.Profile.CONTENT_URI)

private fun getProfileName(context: Context, uri: Uri): String {
    var name: String = DEFAULT_CONTACT_NAME
    val contentResolver = context.contentResolver

    val cursor = contentResolver.query(
        uri,
        arrayOf(PhoneLookup.DISPLAY_NAME),
        null,
        null,
        null,
    )

    cursor?.let {
        val displayName = cursor.getColumnIndex(PhoneLookup.DISPLAY_NAME)

        if (cursor.moveToFirst()) {
            name = cursor.getString(displayName)
        }
        cursor.close()
    }
    return name
}
