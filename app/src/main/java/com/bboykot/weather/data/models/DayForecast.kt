package com.bboykot.weather.data.models

import com.google.gson.annotations.SerializedName

data class DayForecast(
    @SerializedName("list")
    val dayForecast: List<PartDayForecast>
)

data class PartDayForecast(
    @SerializedName("dt")
    val time: Long,
    val main: Main,
    val weather: List<Weather>,
    val wind: Wind,
)