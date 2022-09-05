package com.bboykot.weather.data.remote

import retrofit2.http.GET
import retrofit2.http.Query

interface WeatherApiService {
    @GET("weather?lang=ru&units=metric&appid=6493a6156cae804aa207eb9ee638c79b")
    suspend fun getCurrentForecast(@Query("q") name: String): String//Forecast
}
