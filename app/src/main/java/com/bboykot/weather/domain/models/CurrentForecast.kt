package com.bboykot.weather.domain.models

data class CurrentForecast(
    val id: Long,
    val city: String,
    val temperature: String,
    val wind: String,
    val description: String,
)
