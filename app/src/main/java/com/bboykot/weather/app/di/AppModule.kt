package com.bboykot.weather.app.di

import android.content.Context
import androidx.room.Room
import com.bboykot.weather.data.db.CitiesDatabase
import com.bboykot.weather.data.mappers.MapperCurrentForecastToDb
import com.bboykot.weather.data.mappers.MapperDayToHour
import com.bboykot.weather.data.mappers.MapperForecastToCurrentForecast
import com.bboykot.weather.data.mappers.MapperWeekToDaily
import com.bboykot.weather.data.remote.WeatherApiService
import com.bboykot.weather.data.repository.ForecastRepositoryImpl
import com.bboykot.weather.domain.repository.ForecastRepository
import dagger.Module
import dagger.Provides
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
class AppModule {

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

    @Singleton
    @Provides
    fun provideCitiesDatabase(context: Context): CitiesDatabase {
        return Room.databaseBuilder(context, CitiesDatabase::class.java, "cities").build()
    }

    companion object {
        private const val BASE_URL = "https://api.openweathermap.org/data/2.5/"
    }
}