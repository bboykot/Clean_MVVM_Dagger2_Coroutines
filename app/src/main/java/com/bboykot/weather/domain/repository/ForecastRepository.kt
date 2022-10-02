package com.bboykot.weather.domain.repository

import com.bboykot.weather.domain.models.CurrentForecast
import com.bboykot.weather.domain.models.DailyForecast
import com.bboykot.weather.domain.models.HourForecast
import kotlinx.coroutines.flow.Flow

interface ForecastRepository {

    suspend fun loadCurrentForecastForCity(city: String): CurrentForecast

    suspend fun loadDayForecast(city: String): List<HourForecast>

    suspend fun loadWeekForecast(city: String): List<DailyForecast>

    suspend fun saveCityInDatabase(currentForecast: CurrentForecast?, isDefault: Boolean)

    suspend fun removeCurrentDefaultFlag()

    fun getDefaultCityFromDatabase(): Flow<String?>

    fun getCitiesFromDb(): Flow<List<String>>

    suspend fun deleteCity(city: CurrentForecast)
}