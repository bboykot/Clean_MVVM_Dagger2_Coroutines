package com.bboykot.weather.data.mappers

import android.content.Context
import com.bboykot.weather.R
import com.bboykot.weather.app.Extensions
import com.bboykot.weather.app.getDate
import com.bboykot.weather.data.models.WeekForecast
import com.bboykot.weather.domain.models.DailyForecast
import javax.inject.Inject

class MapperWeekToDaily @Inject constructor(private val context: Context) :
    Mapper<WeekForecast, List<DailyForecast>>() {
    override fun map(from: WeekForecast): List<DailyForecast> {
        return from.weekForecast.map {
            DailyForecast(
                it.time.getDate(Extensions.DAY_PATTERN),
                it.temperature.day.toInt()
                    .toString() + context.getString(R.string.temperature_degree),
                it.temperature.night.toInt()
                    .toString() + context.getString(R.string.temperature_degree),
                it.wind.toInt().toString() + context.getString(R.string.wind_speed),
                it.weather[0].description,
            )
        }
    }
}