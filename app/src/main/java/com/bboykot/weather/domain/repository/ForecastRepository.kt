package com.bboykot.weather.domain.repository

import androidx.lifecycle.LiveData
import com.bboykot.weather.data.models.WeekForecast
import com.bboykot.weather.domain.models.CurrentForecast
import com.bboykot.weather.domain.models.DailyForecast
import com.bboykot.weather.domain.models.HourForecast

interface ForecastRepository {

    suspend fun loadCurrentForecastForCity(city: String): CurrentForecast

    suspend fun loadDayForecast(city: String): List<HourForecast>

    suspend fun loadWeekForecast(city: String) : List<DailyForecast>

    suspend fun saveCityInDatabase(currentForecast: CurrentForecast?, isDefault: Boolean)

    suspend fun removeCurrentDefaultFlag()

    fun getDefaultCityFromDatabase(): LiveData<String?>

    fun getCitiesFromDb(): LiveData<List<String>>

    suspend fun deleteCity(city: CurrentForecast)
}