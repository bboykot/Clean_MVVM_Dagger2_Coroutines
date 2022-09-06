package com.bboykot.weather.app.di.module

import com.bboykot.weather.data.remote.WeatherApiService
import com.bboykot.weather.data.repository.ForecastRepositoryImpl
import com.bboykot.weather.domain.repository.ForecastRepository
import dagger.Module
import dagger.Provides
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.converter.scalars.ScalarsConverterFactory
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
    fun provideForecastRepository(weatherApiService: WeatherApiService): ForecastRepository {
        return ForecastRepositoryImpl(weatherApiService)
    }

    companion object {
        private const val BASE_URL = "https://api.openweathermap.org/data/2.5/"
    }
}