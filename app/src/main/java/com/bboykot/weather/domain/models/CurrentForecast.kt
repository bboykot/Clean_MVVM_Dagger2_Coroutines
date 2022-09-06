package com.bboykot.weather.domain.models

data class CurrentForecast(
    val city: String,
    val temperature: Double,
    val wind: Double,
    val description: String,
)
