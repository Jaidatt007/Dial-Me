package com.example.dialme.call

import android.Manifest
import android.app.Activity
import android.content.ContentResolver
import android.content.pm.PackageManager
import android.provider.CallLog
import android.util.Log
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat


@Composable
fun CallLogScreen() {
    // Get the context for accessing contentResolver
    val context = LocalContext.current
    val contentResolver: ContentResolver = context.contentResolver

    // Check if permission is granted
    if (ContextCompat.checkSelfPermission(context, Manifest.permission.READ_CALL_LOG) == PackageManager.PERMISSION_GRANTED) {
        // Fetch and display call logs
        val callLogs = fetchCallLogs(contentResolver)
        CallLogsList(callLogs)
    } else {
        // Request permission
        ActivityCompat.requestPermissions(context as Activity, arrayOf(Manifest.permission.READ_CALL_LOG), 100)
    }
}

fun fetchCallLogs(contentResolver: ContentResolver): List<CallLogItem> {
    val callLogList = mutableListOf<CallLogItem>()

    val cursor = contentResolver.query(
        CallLog.Calls.CONTENT_URI,
        arrayOf(CallLog.Calls.NUMBER, CallLog.Calls.DATE, CallLog.Calls.DURATION, CallLog.Calls.TYPE),
        null,
        null,
        CallLog.Calls.DATE + " DESC"
    )

    cursor?.let {
        // Check if the cursor contains the necessary columns
        val phoneNumberIndex = it.getColumnIndex(CallLog.Calls.NUMBER)
        val callDateIndex = it.getColumnIndex(CallLog.Calls.DATE)
        val callDurationIndex = it.getColumnIndex(CallLog.Calls.DURATION)
        val callTypeIndex = it.getColumnIndex(CallLog.Calls.TYPE)

        // Ensure all necessary columns exist
        if (phoneNumberIndex >= 0 && callDateIndex >= 0 && callDurationIndex >= 0 && callTypeIndex >= 0) {
            while (it.moveToNext()) {
                val phoneNumber = it.getString(phoneNumberIndex)
                val callDate = it.getLong(callDateIndex)
                val callDuration = it.getInt(callDurationIndex)
                val callType = it.getInt(callTypeIndex)

                val callTypeStr = when (callType) {
                    CallLog.Calls.INCOMING_TYPE -> "Incoming"
                    CallLog.Calls.OUTGOING_TYPE -> "Outgoing"
                    CallLog.Calls.MISSED_TYPE -> "Missed"
                    else -> "Unknown"
                }

                val formattedDate = java.text.SimpleDateFormat("yyyy-MM-dd HH:mm:ss")
                    .format(java.util.Date(callDate))

                callLogList.add(CallLogItem(phoneNumber, callTypeStr, callDuration, formattedDate))
            }
        } else {
            Log.e("CallLog", "One or more necessary columns are missing!")
        }

        it.close()
    }

    return callLogList
}

@Composable
fun CallLogsList(callLogs: List<CallLogItem>) {
    Column(modifier = Modifier.padding(16.dp)) {
        callLogs.forEach { callLog ->
            CallLogItemView(callLogItem = callLog)
        }
    }
}

@Composable
fun CallLogItemView(callLogItem: CallLogItem) {
    Column(modifier = Modifier.padding(8.dp)) {
        Text(text = "Phone Number: ${callLogItem.phoneNumber}", fontSize = 16.sp)
        Text(text = "Call Type: ${callLogItem.callType}", fontSize = 14.sp)
        Text(text = "Duration: ${callLogItem.callDuration} seconds", fontSize = 14.sp)
        Text(text = "Date: ${callLogItem.callDate}", fontSize = 14.sp)
    }
}

data class CallLogItem(
    val phoneNumber: String,
    val callType: String,
    val callDuration: Int,
    val callDate: String
)

