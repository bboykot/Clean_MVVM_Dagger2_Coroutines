package com.bboykot.weather.app.mappers

import android.content.Context
import com.bboykot.weather.R
import com.bboykot.weather.data.models.Forecast
import com.bboykot.weather.domain.models.CurrentForecast
import javax.inject.Inject

class MapperForecastToCurrentForecast @Inject constructor(private val context: Context) : Mapper<Forecast, CurrentForecast>() {
    override fun map(from: Forecast): CurrentForecast {
        return CurrentForecast(
            from.id,
            from.city,
            from.main.temperature.toInt().toString() + context.getString(R.string.temperature_degree),
            from.wind.speed.toInt().toString() + context.getString(R.string.wind_speed),
            from.weather[0].description,
        )
    }
}