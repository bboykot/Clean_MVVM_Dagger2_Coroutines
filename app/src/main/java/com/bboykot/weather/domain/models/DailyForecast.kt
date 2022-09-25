package com.bboykot.weather.domain.models

data class DailyForecast(
    val time: String,
    val dayTemperature: Double,
    val nightTemperature: Double,
    val wind: Double,
)