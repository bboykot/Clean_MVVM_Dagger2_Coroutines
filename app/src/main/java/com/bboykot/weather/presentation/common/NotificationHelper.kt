package com.bboykot.weather.presentation.common

import android.content.Context
import com.bboykot.weather.R
import javax.inject.Inject

class NotificationHelper @Inject constructor(context: Context) {
    val noDefaultCity = context.getString(R.string.no_default_city)
    val noCities = context.getString(R.string.no_cities)
}