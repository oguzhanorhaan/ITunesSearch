package com.oguzhanorhan.itunessearch.common

import java.lang.Exception
import java.text.SimpleDateFormat
import java.util.*

object DateFormat {
    const val appDateFormat = "dd/MM/yyyy HH:mm"
    const val serviceDateFormat = "yyyy-MM-dd'T'HH:mm:ss'Z'"
}

fun String.formatDate(
    currentFormat: String = DateFormat.serviceDateFormat,
    newFormat: String = DateFormat.appDateFormat
): String {

    return try {
        val simpleDateFormat = SimpleDateFormat(currentFormat, Locale.ENGLISH)
        simpleDateFormat.timeZone = TimeZone.getTimeZone("UTC")
        val currentDate: Date = simpleDateFormat.parse(this) ?: Date()
        val newDateFormat = SimpleDateFormat(newFormat, Locale.ENGLISH)
        newDateFormat.timeZone = TimeZone.getDefault()
        newDateFormat.format(currentDate)
    } catch (e: Exception) {
        this
    }
}