package com.bboykot.weather.data.remote

import com.bboykot.weather.data.models.DayForecast
import com.bboykot.weather.data.models.Forecast
import com.bboykot.weather.data.models.WeekForecast
import retrofit2.http.GET
import retrofit2.http.Query

interface WeatherApiService {
    @GET("weather?lang=ru&units=metric&appid=6493a6156cae804aa207eb9ee638c79b")
    suspend fun getCurrentForecast(@Query("q") name: String): Forecast

    @GET("forecast?cnt=8&lang=ru&units=metric&appid=6493a6156cae804aa207eb9ee638c79b")
    suspend fun getDayForecast(@Query("q") name: String): DayForecast

    @GET("forecast/daily?units=metric&cnt=7&lang=ru&appid=c0c4a4b4047b97ebc5948ac9c48c0559")
    suspend fun getWeekForecast(@Query("q") name: String): WeekForecast
}
