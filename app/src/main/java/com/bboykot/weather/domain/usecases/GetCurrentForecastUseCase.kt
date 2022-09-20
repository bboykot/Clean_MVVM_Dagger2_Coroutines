package com.bboykot.weather.domain.usecases

import com.bboykot.weather.domain.models.CurrentForecast
import com.bboykot.weather.domain.repository.ForecastRepository
import javax.inject.Inject

class GetCurrentForecastUseCase @Inject constructor(private val forecastRepository: ForecastRepository) {
    suspend fun fetch(city: String): CurrentForecast {
        return forecastRepository.loadCurrentForecastForCity(city)
    }
}