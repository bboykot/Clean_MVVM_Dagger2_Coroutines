package com.bboykot.weather.domain.usecases

import androidx.lifecycle.LiveData
import com.bboykot.weather.domain.repository.ForecastRepository
import javax.inject.Inject

class GetDefaultCityUseCase @Inject constructor(private val forecastRepository: ForecastRepository) {
    fun fetch(): LiveData<String?>{
        return forecastRepository.getDefaultCityFromDatabase()
    }
}