package com.mk.assessment

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.telephony.TelephonyManager
import callLogs.getContactName
import com.mk.infrastructure.phoneSession.PhoneCallSessionManager
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class OngoingCallReceiver : BroadcastReceiver() {
    @Inject lateinit var phoneCallSessionManager: PhoneCallSessionManager

    override fun onReceive(context: Context, intent: Intent) {
        if (intent.action.equals(Intent.ACTION_NEW_OUTGOING_CALL)) {
            val number = intent.getStringExtra(Intent.EXTRA_PHONE_NUMBER).toString()
            val name = getContactName(context, number)
            phoneCallSessionManager.setPhoneCallSession(name, number, true)
        } else if (intent.action.equals("android.intent.action.PHONE_STATE")) {
            val state = intent.getStringExtra(TelephonyManager.EXTRA_STATE)

            if (state.equals(TelephonyManager.EXTRA_STATE_RINGING)) {
                val number = intent.getStringExtra(TelephonyManager.EXTRA_INCOMING_NUMBER).toString()
                val name = getContactName(context, number)
                phoneCallSessionManager.setPhoneCallSession(name, number, true)
            } else if (state.equals(TelephonyManager.EXTRA_STATE_OFFHOOK)) {
                val number = intent.getStringExtra(TelephonyManager.EXTRA_INCOMING_NUMBER).toString()
                val name = getContactName(context, number)
                phoneCallSessionManager.setPhoneCallSession(name, number, true)
            } else if (state.equals(TelephonyManager.EXTRA_STATE_IDLE)) {
                phoneCallSessionManager.setPhoneCallEnded()
            }
        }
    }
}
