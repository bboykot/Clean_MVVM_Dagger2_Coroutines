package com.bboykot.weather.data.models

import com.google.gson.annotations.SerializedName

data class WeekForecast(
    @SerializedName("list")
    val weekForecast: List<DayOfWeekForecast>
)

data class DayOfWeekForecast(
    @SerializedName("dt")
    val time: Long,
    @SerializedName("speed")
    val wind: Double,
    val weather: List<Weather>,
    @SerializedName("temp")
    val temperature: Temperature,
)

data class Temperature(
    val day: Double,
    val night: Double,
)