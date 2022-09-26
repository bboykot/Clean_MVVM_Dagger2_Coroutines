package com.bboykot.weather.data.repository

import androidx.lifecycle.LiveData
import com.bboykot.weather.app.getDateTime
import com.bboykot.weather.app.getDay
import com.bboykot.weather.data.db.CitiesDatabase
import com.bboykot.weather.data.db.CitiesEntity
import com.bboykot.weather.data.models.WeekForecast
import com.bboykot.weather.data.remote.WeatherApiService
import com.bboykot.weather.domain.models.CurrentForecast
import com.bboykot.weather.domain.models.DailyForecast
import com.bboykot.weather.domain.models.HourForecast
import com.bboykot.weather.domain.repository.ForecastRepository

class ForecastRepositoryImpl(
    private val weatherApiService: WeatherApiService,
    private val citiesDatabase: CitiesDatabase,
) : ForecastRepository {

    override suspend fun loadCurrentForecastForCity(city: String): CurrentForecast {
        val forecast = weatherApiService.getCurrentForecast(city)
        return CurrentForecast(
            forecast.id,
            forecast.city,
            forecast.main.temperature,
            forecast.wind.speed,
            forecast.weather[0].description,
        )
    }

    override suspend fun loadDayForecast(city: String): List<HourForecast> {
        val forecast = weatherApiService.getDayForecast(city)
        return forecast.dayForecast.map {
            HourForecast(
                it.time.getDateTime(),
                it.main.temperature,
                it.wind.speed,
                it.weather[0].description,
            )
        }
    }

    override suspend fun loadWeekForecast(city: String): List<DailyForecast> {
        val forecast = weatherApiService.getWeekForecast(city)
        return forecast.weekForecast.map {
            DailyForecast(
                it.time.getDay(),
                it.temperature.day,
                it.temperature.night,
                it.wind,
                it.weather[0].description,
            )
        }
    }

    override suspend fun saveCityInDatabase(currentForecast: CurrentForecast?, isDefault: Boolean) {
        currentForecast?.let {
            citiesDatabase.getCitiesDao().insertCity(CitiesEntity(it.id, it.city, isDefault))
        }
    }

    override suspend fun removeCurrentDefaultFlag() {
        citiesDatabase.getCitiesDao().removeCurrentDefaultFlag()
    }

    override fun getDefaultCityFromDatabase(): LiveData<String?> {
        return citiesDatabase.getCitiesDao().getDefaultCity()
    }

    override fun getCitiesFromDb(): LiveData<List<String>> {
        return citiesDatabase.getCitiesDao().getCitiesList()
    }

    override suspend fun deleteCity(city: CurrentForecast) {
        citiesDatabase.getCitiesDao().deleteCity(CitiesEntity(city.id, city.city, false))
    }
}