package com.bboykot.weather.domain.models

data class HourForecast(
    val time: String,
    val temperature: Double,
    val wind: Double,
    val description: String,
)
