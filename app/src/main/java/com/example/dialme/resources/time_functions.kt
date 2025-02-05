package com.example.dialme.resources

import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

fun getDateAndTimeInMillis() : Long{
    return System.currentTimeMillis()
}

fun formatMillisToDate(millis: Long): String {
    val sdf = SimpleDateFormat("dd/MM/yyyy", Locale.getDefault())
    return sdf.format(Date(millis))
}
fun formatMillisToTime(millis: Long): String {
    val sdf = SimpleDateFormat("hh:mm:a", Locale.getDefault())
    return sdf.format(Date(millis))
}