package com.bboykot.weather.data.repository

import com.bboykot.weather.data.db.CitiesDatabase
import com.bboykot.weather.data.remote.WeatherApiService
import com.bboykot.weather.domain.models.CurrentForecast
import com.bboykot.weather.domain.repository.ForecastRepository

class ForecastRepositoryImpl(
    private val weatherApiService: WeatherApiService,
    private val citiesDatabase: CitiesDatabase,
): ForecastRepository {

    override suspend fun loadCurrentForecastForCity(city: String): CurrentForecast {
        val forecast = weatherApiService.getCurrentForecast(city)
        return CurrentForecast(forecast.city, forecast.main.temperature, forecast.wind.speed, forecast.weather[0].description)
    }
}