package com.bboykot.weather.domain.usecases

import com.bboykot.weather.domain.repository.ForecastRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetDefaultCityUseCase @Inject constructor(private val forecastRepository: ForecastRepository) {
    fun fetch(): Flow<String?>{
        return forecastRepository.getDefaultCityFromDatabase()
    }
}