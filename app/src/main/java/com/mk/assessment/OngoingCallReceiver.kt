package com.mk.assessment

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.telephony.TelephonyManager
import callLogs.getContactName
import com.mk.infrastructure.phoneSession.PhoneCallSessionManager
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

/**
 * A BroadcastReceiver that listens for phone call events (outgoing, incoming, ringing, offhook, idle)
 * and manages the phone call session using [PhoneCallSessionManager].
 *
 * This receiver is responsible for:
 * - Detecting new outgoing calls and storing the contact's name and number.
 * - Detecting incoming calls and storing the contact's name and number when the phone is ringing.
 * - Detecting when the phone goes offhook and storing the contact's name and number.
 * - Detecting when the phone call ends (goes idle) and informing the [PhoneCallSessionManager].
 */
@AndroidEntryPoint
class OngoingCallReceiver : BroadcastReceiver() {
    @Inject lateinit var phoneCallSessionManager: PhoneCallSessionManager

    // I should probably get the devices logs to update the phone number
    override fun onReceive(context: Context, intent: Intent) {
        when (intent.action) {
            Intent.ACTION_NEW_OUTGOING_CALL -> {
                val number = intent.getStringExtra(Intent.EXTRA_PHONE_NUMBER).toString()
                val contact = getContactName(context, number)
                phoneCallSessionManager.setPhoneCallSession(contact, number, true)
            }
            "android.intent.action.PHONE_STATE" -> {
                val state = intent.getStringExtra(TelephonyManager.EXTRA_STATE)

                when (state) {
                    TelephonyManager.EXTRA_STATE_RINGING,
                    TelephonyManager.EXTRA_STATE_OFFHOOK,
                    -> {
                        val number = intent.getStringExtra(TelephonyManager.EXTRA_INCOMING_NUMBER).toString()
                        val contact = getContactName(context, number)
                        phoneCallSessionManager.setPhoneCallSession(contact, number, true)
                    }
                    TelephonyManager.EXTRA_STATE_IDLE -> {
                        phoneCallSessionManager.setPhoneCallEnded()
                    }
                }
            }
        }
    }
}
