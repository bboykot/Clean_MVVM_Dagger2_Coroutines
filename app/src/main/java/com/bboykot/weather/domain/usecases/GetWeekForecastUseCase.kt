package com.bboykot.weather.domain.usecases

import com.bboykot.weather.domain.models.DailyForecast
import com.bboykot.weather.domain.repository.ForecastRepository
import javax.inject.Inject

class GetWeekForecastUseCase @Inject constructor(
    private val forecastRepository: ForecastRepository,
) {
    suspend fun fetch(city: String) : List<DailyForecast>{
        return forecastRepository.loadWeekForecast(city)
    }
}