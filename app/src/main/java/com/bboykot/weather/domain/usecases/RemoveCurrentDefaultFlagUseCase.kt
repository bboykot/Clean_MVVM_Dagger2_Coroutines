package com.bboykot.weather.domain.usecases

import com.bboykot.weather.domain.repository.ForecastRepository
import javax.inject.Inject

class RemoveCurrentDefaultFlagUseCase @Inject constructor(
    private val forecastRepository: ForecastRepository
) {
    suspend fun removeCurrentDefaultFlag(){
        forecastRepository.removeCurrentDefaultFlag()
    }
}