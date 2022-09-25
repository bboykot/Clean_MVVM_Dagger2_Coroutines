package com.bboykot.weather.app

import java.text.SimpleDateFormat
import java.util.*

fun Long.getDateTime(): String{
    val dateTime = Date(Extensions.TIME_FACTOR * this)
    val format = SimpleDateFormat(Extensions.TIME_PATTERN, Locale.US)
    return format.format(dateTime)
}

object Extensions {
    const val TIME_FACTOR = 1000
    const val TIME_PATTERN = "HH:mm dd.MM.yyyy"
}