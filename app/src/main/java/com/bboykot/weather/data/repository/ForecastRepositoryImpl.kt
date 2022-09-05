package com.bboykot.weather.data.repository

import com.bboykot.weather.domain.models.CurrentForecast
import com.bboykot.weather.domain.repository.ForecastRepository

class ForecastRepositoryImpl: ForecastRepository {

    override suspend fun loadCurrentForecastForCity(city: String): CurrentForecast {
//        val forecast = WeatherApi.retrofitService.getCurrentForecast("Rostov")
//        return CurrentForecast(forecast.city, forecast.temperature, forecast.wind, forecast.description)
        return CurrentForecast("forecast.city", 20, 20.2, "forecast.description")
    }
}