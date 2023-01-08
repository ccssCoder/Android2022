package com.example.a3track.utils

import java.text.SimpleDateFormat
import java.util.*

/**
Convert long to Time & Date String:
*/
fun Long.toTimeDateString(): String {
    val dateTime = Date(this)
    val format = SimpleDateFormat("dd MMMM yyyy", Locale.US)
    return format.format(dateTime)
}

/**
Convert Time & Date String to Long:
 */
fun String.toTimeDateLong(): Long {
    val format = SimpleDateFormat("dd MM yyyy", Locale.US)
    return format.parse(this)?.time ?: throw IllegalArgumentException("Invalid time string")
}