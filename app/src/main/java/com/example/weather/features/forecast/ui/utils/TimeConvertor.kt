package com.example.weather.features.forecast.ui.utils

import java.text.SimpleDateFormat
import java.time.LocalTime
import java.util.Date

fun String.extractTime(): LocalTime {
    val localDateTime = LocalTime.parse(this)
    return localDateTime
}

fun convertLongToTime(time: Long): String {
    val date = Date(time)
    val format = SimpleDateFormat("HH:mm")
    return format.format(date)
}

fun String.convertToTemperature() = buildString {
    append(this@convertToTemperature.substringBefore("."))
    append("°C")
}