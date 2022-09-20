package com.bboykot.weather.domain.repository

import androidx.lifecycle.LiveData
import com.bboykot.weather.domain.models.CurrentForecast

interface ForecastRepository {

    suspend fun loadCurrentForecastForCity(city: String): CurrentForecast

    suspend fun saveCityInDatabase(currentForecast: CurrentForecast?, isDefault: Boolean)

    suspend fun removeCurrentDefaultFlag()

    fun getDefaultCityFromDatabase(): LiveData<String?>

    fun getCitiesFromDb(): LiveData<List<String>>
}