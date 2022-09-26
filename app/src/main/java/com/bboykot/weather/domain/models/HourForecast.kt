package com.bboykot.weather.domain.models

data class HourForecast(
    val time: String,
    val temperature: String,
    val wind: String,
    val description: String,
)
