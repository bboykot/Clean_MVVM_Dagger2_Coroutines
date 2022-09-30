package com.bboykot.weather.data.repository

import androidx.lifecycle.LiveData
import com.bboykot.weather.app.mappers.MapperCurrentForecastToDb
import com.bboykot.weather.app.mappers.MapperDayToHour
import com.bboykot.weather.app.mappers.MapperForecastToCurrentForecast
import com.bboykot.weather.app.mappers.MapperWeekToDaily
import com.bboykot.weather.data.db.CitiesDatabase
import com.bboykot.weather.data.remote.WeatherApiService
import com.bboykot.weather.domain.models.CurrentForecast
import com.bboykot.weather.domain.models.DailyForecast
import com.bboykot.weather.domain.models.HourForecast
import com.bboykot.weather.domain.repository.ForecastRepository
import kotlinx.coroutines.flow.Flow

class ForecastRepositoryImpl(
    private val weatherApiService: WeatherApiService,
    private val citiesDatabase: CitiesDatabase,
    private val mapperForecastToCurrentForecast: MapperForecastToCurrentForecast,
    private val mapperDayToHour: MapperDayToHour,
    private val mapperWeekToDaily: MapperWeekToDaily,
    private val mapperCurrentForecastToDb: MapperCurrentForecastToDb,
) : ForecastRepository {

    override suspend fun loadCurrentForecastForCity(city: String): CurrentForecast {
        val forecast = weatherApiService.getCurrentForecast(city)
        return mapperForecastToCurrentForecast.map(forecast)
    }

    override suspend fun loadDayForecast(city: String): List<HourForecast> {
        val forecast = weatherApiService.getDayForecast(city)
        return mapperDayToHour.map(forecast)
    }

    override suspend fun loadWeekForecast(city: String): List<DailyForecast> {
        val forecast = weatherApiService.getWeekForecast(city)
        return mapperWeekToDaily.map(forecast)
    }

    override suspend fun saveCityInDatabase(currentForecast: CurrentForecast?, isDefault: Boolean) {
        currentForecast?.let {
            citiesDatabase.getCitiesDao().insertCity(mapperCurrentForecastToDb.map(currentForecast to isDefault))
        }
    }

    override suspend fun removeCurrentDefaultFlag() {
        citiesDatabase.getCitiesDao().removeCurrentDefaultFlag()
    }

    override fun getDefaultCityFromDatabase(): Flow<String?> {
        return citiesDatabase.getCitiesDao().getDefaultCity()
    }

    override fun getCitiesFromDb(): Flow<List<String>> {
        return citiesDatabase.getCitiesDao().getCitiesList()
    }

    override suspend fun deleteCity(currentForecast: CurrentForecast) {
        citiesDatabase.getCitiesDao().deleteCity(mapperCurrentForecastToDb.map(currentForecast to false))
    }
}