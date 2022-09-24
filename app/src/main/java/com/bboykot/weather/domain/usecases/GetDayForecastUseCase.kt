package com.bboykot.weather.domain.usecases

import com.bboykot.weather.domain.models.CurrentForecast
import com.bboykot.weather.domain.models.HourForecast
import com.bboykot.weather.domain.repository.ForecastRepository
import javax.inject.Inject

class GetDayForecastUseCase @Inject constructor(
    private val repository: ForecastRepository,
) {
    suspend fun fetch(city: String): List<HourForecast>{
        return repository.loadDayForecast(city)
    }
}