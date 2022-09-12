package com.bboykot.weather.domain.usecases

import com.bboykot.weather.domain.repository.ForecastRepository
import javax.inject.Inject

class GetDefaultCityUseCase @Inject constructor(private val forecastRepository: ForecastRepository) {
    suspend fun fetch(): String?{
        return forecastRepository.getDefaultCityFromDatabase()
    }
}