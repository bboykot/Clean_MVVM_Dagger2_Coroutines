package com.bboykot.weather.domain.repository

import com.bboykot.weather.domain.models.CurrentForecast

interface ForecastRepository {

    fun loadCurrentForecastForCity(city: String): CurrentForecast
}