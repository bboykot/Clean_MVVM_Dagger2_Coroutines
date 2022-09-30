package com.bboykot.weather.app.di

import com.bboykot.weather.app.mappers.MapperCurrentForecastToDb
import com.bboykot.weather.app.mappers.MapperDayToHour
import com.bboykot.weather.app.mappers.MapperForecastToCurrentForecast
import com.bboykot.weather.app.mappers.MapperWeekToDaily
import com.bboykot.weather.data.db.CitiesDatabase
import com.bboykot.weather.data.remote.WeatherApiService
import com.bboykot.weather.data.repository.ForecastRepositoryImpl
import com.bboykot.weather.domain.repository.ForecastRepository
import dagger.Module
import dagger.Provides
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
class NetworkModule {

    @Singleton
    @Provides
    fun provideWeatherApiService(): WeatherApiService {
        return Retrofit.Builder()
            .addConverterFactory(GsonConverterFactory.create())
            .baseUrl(BASE_URL)
            .build()
            .create(WeatherApiService::class.java)
    }

    @Singleton
    @Provides
    fun provideForecastRepository(
        weatherApiService: WeatherApiService,
        citiesDatabase: CitiesDatabase,
        mapperForecastToCurrentForecast: MapperForecastToCurrentForecast,
        mapperDayToHour: MapperDayToHour,
        mapperWeekToDaily: MapperWeekToDaily,
        mapperCurrentForecastToDb: MapperCurrentForecastToDb,
    ): ForecastRepository {
        return ForecastRepositoryImpl(
            weatherApiService,
            citiesDatabase,
            mapperForecastToCurrentForecast,
            mapperDayToHour,
            mapperWeekToDaily,
            mapperCurrentForecastToDb,
        )
    }

    companion object {
        private const val BASE_URL = "https://api.openweathermap.org/data/2.5/"
    }
}