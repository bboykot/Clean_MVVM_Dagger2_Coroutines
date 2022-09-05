package com.bboykot.weather.data.models

data class Forecast(
    val id: Long,
    val city: String,
    val temperature: Int,
    val wind: Double,
    val description: String,
)