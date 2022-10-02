package com.bboykot.weather.data.remote

import com.bboykot.weather.data.models.DayForecast
import com.bboykot.weather.data.models.Forecast
import com.bboykot.weather.data.models.WeekForecast
import retrofit2.http.GET
import retrofit2.http.Query

interface WeatherApiService {
    @GET(CURRENT_FORECAST)
    suspend fun getCurrentForecast(@Query("q") name: String): Forecast

    @GET(DAY_FORECAST)
    suspend fun getDayForecast(@Query("q") name: String): DayForecast

    @GET(WEEK_FORECAST)
    suspend fun getWeekForecast(@Query("q") name: String): WeekForecast

    companion object {
        private const val CURRENT_FORECAST = "weather?lang=en&units=metric&appid=6493a6156cae804aa207eb9ee638c79b"
        private const val DAY_FORECAST = "forecast?cnt=8&lang=en&units=metric&appid=6493a6156cae804aa207eb9ee638c79b"
        private const val WEEK_FORECAST = "forecast/daily?units=metric&cnt=7&lang=en&appid=c0c4a4b4047b97ebc5948ac9c48c0559"
    }
}
