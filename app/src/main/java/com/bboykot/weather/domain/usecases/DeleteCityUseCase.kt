package com.bboykot.weather.domain.usecases

import com.bboykot.weather.domain.repository.ForecastRepository
import javax.inject.Inject

class DeleteCityUseCase @Inject constructor(private val repository: ForecastRepository) {
    suspend fun fetch(city: String) {
        repository.deleteCity(city)
    }
}