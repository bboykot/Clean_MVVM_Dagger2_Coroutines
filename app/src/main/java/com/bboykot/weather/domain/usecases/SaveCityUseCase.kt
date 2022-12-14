package com.bboykot.weather.domain.usecases

import com.bboykot.weather.domain.models.CurrentForecast
import com.bboykot.weather.domain.repository.ForecastRepository
import javax.inject.Inject

class SaveCityUseCase @Inject constructor(
    private val forecastRepository: ForecastRepository
) {
    suspend fun saveCity(currentForecast: CurrentForecast?, isDefaultCity: Boolean) {
        forecastRepository.saveCityInDatabase(currentForecast, isDefaultCity)
    }
}