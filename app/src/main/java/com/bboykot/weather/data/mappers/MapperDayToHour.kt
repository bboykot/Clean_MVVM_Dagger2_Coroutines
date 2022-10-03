package com.bboykot.weather.data.mappers

import android.content.Context
import com.bboykot.weather.R
import com.bboykot.weather.app.Extensions
import com.bboykot.weather.app.getDate
import com.bboykot.weather.data.models.DayForecast
import com.bboykot.weather.domain.models.HourForecast
import javax.inject.Inject

class MapperDayToHour @Inject constructor(private val context: Context) :
    Mapper<DayForecast, List<HourForecast>>() {
    override fun map(from: DayForecast): List<HourForecast> {
        return from.dayForecast.map {
            HourForecast(
                it.time.getDate(Extensions.DAY_TIME_PATTERN),
                it.main.temperature.toInt().toString() + context.getString(R.string.temperature_degree),
                it.wind.speed.toInt().toString() + context.getString(R.string.wind_speed),
                it.weather[0].description,
            )
        }
    }
}