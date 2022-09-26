package com.bboykot.weather.app.mappers

import android.content.Context
import com.bboykot.weather.R
import com.bboykot.weather.app.getDateTime
import com.bboykot.weather.data.models.DayForecast
import com.bboykot.weather.domain.models.HourForecast
import javax.inject.Inject

class MapperDayToHour @Inject constructor(private val context: Context) :
    Mapper<DayForecast, List<HourForecast>>() {
    override fun map(from: DayForecast): List<HourForecast> {
        return from.dayForecast.map {
            HourForecast(
                it.time.getDateTime(),
                it.main.temperature.toInt().toString() + context.getString(R.string.temperature_degree),
                it.wind.speed.toInt().toString() + context.getString(R.string.wind_speed),
                it.weather[0].description,
            )
        }
    }
}