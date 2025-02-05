package com.example.dialme.call

import android.content.ContentResolver
import android.database.Cursor
import android.provider.CallLog
import android.util.Log
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

data class CallInfo(
    val phoneNumber: String,
    val callDate: String,
    val callDuration: Int,
    val callType: String
)

fun getCallDetails(contentResolver: ContentResolver): List<CallInfo> {
    val callInfoList = mutableListOf<CallInfo>()

    val projection = arrayOf(
        CallLog.Calls.NUMBER,
        CallLog.Calls.DATE,
        CallLog.Calls.DURATION,
        CallLog.Calls.TYPE
    )

    val sortOrder = CallLog.Calls.DATE + " DESC"
    val cursor: Cursor? = contentResolver.query(
        CallLog.Calls.CONTENT_URI,
        projection,
        null,
        null,
        sortOrder
    )

    cursor?.let {
        while (it.moveToNext()) {
            // Get column indices for each field
            val phoneNumberIndex = it.getColumnIndex(CallLog.Calls.NUMBER)
            val callDateIndex = it.getColumnIndex(CallLog.Calls.DATE)
            val callDurationIndex = it.getColumnIndex(CallLog.Calls.DURATION)
            val callTypeIndex = it.getColumnIndex(CallLog.Calls.TYPE)

            // Check if all column indices are valid (≥ 0)
            if (phoneNumberIndex >= 0 && callDateIndex >= 0 && callDurationIndex >= 0 && callTypeIndex >= 0) {
                // Safely retrieve column data using valid indices
                val phoneNumber = it.getString(phoneNumberIndex)
                val callDate = it.getLong(callDateIndex)
                val callDuration = it.getInt(callDurationIndex)
                val callType = it.getInt(callTypeIndex)

                // Convert the call type to a readable string
                val callTypeStr = when (callType) {
                    CallLog.Calls.INCOMING_TYPE -> "Incoming"
                    CallLog.Calls.OUTGOING_TYPE -> "Outgoing"
                    CallLog.Calls.MISSED_TYPE -> "Missed"
                    else -> "Unknown"
                }

                // Format the call date to a readable format
                val formattedDate = SimpleDateFormat("yyyy-MM-dd HH:mm:ss")
                    .format(Date(callDate))

                // Log or display the information
                Log.d("CallLog", "Phone: $phoneNumber, Type: $callTypeStr, Duration: $callDuration seconds, Date: $formattedDate")
            } else {
                // Handle missing columns gracefully (log an error or skip the entry)
                Log.e("CallLogError", "One or more columns are missing in the cursor.")
            }
        }
        it.close() // Close the cursor when done
    }


    return callInfoList
}

