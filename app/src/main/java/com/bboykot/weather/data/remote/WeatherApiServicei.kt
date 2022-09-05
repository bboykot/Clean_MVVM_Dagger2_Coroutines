package com.bboykot.weather.data.remote

import com.bboykot.weather.data.models.Forecast
import retrofit2.http.GET
import retrofit2.http.Query

interface WeatherApiService {
    @GET("weather?lang=ru&units=metric&appid=6493a6156cae804aa207eb9ee638c79b")
    suspend fun getCurrentForecast(@Query("q") name: String): Forecast
}
