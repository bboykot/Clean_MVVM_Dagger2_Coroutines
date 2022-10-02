package com.bboykot.weather.data.models

import com.google.gson.annotations.SerializedName

data class Forecast(
    @SerializedName("id")
    val id: Long,
    @SerializedName("name")
    val city: String,
    val main: Main,
    val weather: List<Weather>,
    val wind: Wind,
)

data class Main(
    @SerializedName("temp")
    val temperature: Double,
)

data class Weather(
    @SerializedName("description")
    val description: String,
)

data class Wind(
    @SerializedName("speed")
    val speed: Double,
)