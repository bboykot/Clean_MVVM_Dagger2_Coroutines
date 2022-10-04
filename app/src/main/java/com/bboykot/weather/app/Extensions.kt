package com.bboykot.weather.app

import androidx.fragment.app.Fragment
import com.bboykot.weather.presentation.common.MainActivity
import java.text.SimpleDateFormat
import java.util.*

fun Long.getDate(pattern: String): String {
    val dateTime = Date(Extensions.TIME_FACTOR * this)
    val format = SimpleDateFormat(pattern, Locale.US)
    return format.format(dateTime)
}

fun Fragment.callMainActivity() = activity as MainActivity
fun Fragment.callAppComponent() = (activity?.application as App).appComponent

object Extensions {
    const val TIME_FACTOR = 1000
    const val DAY_TIME_PATTERN = "HH:mm dd.MM.yyyy"
    const val DAY_PATTERN = "dd.MM.yyyy"
}