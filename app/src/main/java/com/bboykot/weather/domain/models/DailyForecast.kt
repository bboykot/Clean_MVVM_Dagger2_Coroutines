package com.bboykot.weather.domain.models

data class DailyForecast(
    val time: String,
    val dayTemperature: String,
    val nightTemperature: String,
    val wind: String,
    val description: String,
)