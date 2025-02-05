package com.example.dialme.call

import android.content.Context
import android.content.Intent
import android.net.Uri
import android.widget.Toast

fun makeCall(context: Context, phoneNumber: String) {
    val callIntent = Intent(Intent.ACTION_CALL)
    callIntent.data = Uri.parse("tel:$phoneNumber")

    // Check if CALL_PHONE permission is granted
    if (context.checkSelfPermission(android.Manifest.permission.CALL_PHONE) == android.content.pm.PackageManager.PERMISSION_GRANTED) {
        context.startActivity(callIntent)
    } else {
        Toast.makeText(context, "Permission denied! Enable CALL_PHONE permission.", Toast.LENGTH_SHORT).show()
    }
}
