package com.example.dialme.call

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.telephony.TelephonyManager
import android.util.Log

class PhoneCallReceiver : BroadcastReceiver() {

    override fun onReceive(context: Context, intent: Intent?) {
        val state = intent?.getStringExtra(TelephonyManager.EXTRA_STATE)
        val incomingNumber = intent?.getStringExtra(TelephonyManager.EXTRA_INCOMING_NUMBER)

        when (state) {
            TelephonyManager.EXTRA_STATE_RINGING -> {
                // Incoming call is ringing
                Log.d("PhoneCallReceiver", "Incoming call from: $incomingNumber")
            }
            TelephonyManager.EXTRA_STATE_OFFHOOK -> {
                // Call answered (either incoming or outgoing)
                Log.d("PhoneCallReceiver", "Call answered")
            }
            TelephonyManager.EXTRA_STATE_IDLE -> {
                // Call ended (either incoming, outgoing, or missed)
                Log.d("PhoneCallReceiver", "Call ended")
            }
        }
    }
}
