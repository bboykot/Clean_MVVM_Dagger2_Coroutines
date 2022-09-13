package com.bboykot.weather.data.repository

import androidx.lifecycle.LiveData
import com.bboykot.weather.data.db.CitiesDatabase
import com.bboykot.weather.data.db.CitiesEntity
import com.bboykot.weather.data.remote.WeatherApiService
import com.bboykot.weather.domain.models.CurrentForecast
import com.bboykot.weather.domain.repository.ForecastRepository

class ForecastRepositoryImpl(
    private val weatherApiService: WeatherApiService,
    private val citiesDatabase: CitiesDatabase,
): ForecastRepository {

    override suspend fun loadCurrentForecastForCity(city: String): CurrentForecast {
        val forecast = weatherApiService.getCurrentForecast(city)
        return CurrentForecast(forecast.id, forecast.city, forecast.main.temperature, forecast.wind.speed, forecast.weather[0].description)
    }

    override suspend fun saveCityInDatabase(currentForecast: CurrentForecast?, isDefault: Boolean) {
        currentForecast?.let{
            citiesDatabase.getCitiesDao().insertCity(CitiesEntity(it.id, it.city, isDefault))
        }
    }

    override suspend fun removeCurrentDefaultFlag() {
        citiesDatabase.getCitiesDao().removeCurrentDefaultFlag()
    }

    override fun getDefaultCityFromDatabase(): LiveData<String?> {
        return citiesDatabase.getCitiesDao().getDefaultCity()
    }
}