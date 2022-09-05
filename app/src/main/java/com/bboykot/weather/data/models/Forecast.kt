package com.bboykot.weather.data.models

import com.google.gson.annotations.SerializedName

data class Forecast(
    @SerializedName("id")
    val id: Long,
    @SerializedName("name")
    val city: String,
    val main: Main,
    val weather: List<Weather>,
    val wind: Wind,
)

data class Main(
    @SerializedName("temp")
    val temperature: Double,
)

data class Weather(
    @SerializedName("description")
    val description: String,
)

data class Wind(
    @SerializedName("speed")
    val speed: Double,
)



//
//{"coord":{"lon":39.4139,"lat":57.1914},"weather":[{"id":502,"main":"Rain","description":"сильный дождь","icon":"10d"}],
//    "base":"stations","main":{"temp":9.43,"feels_like":7.46,"temp_min":9.43,"temp_max":9.43,"pressure":1019,"humidity":77,"sea_level":1019,"grnd_level":1007},
//    "visibility":10000,"wind":{"speed":3.67,"deg":11,"gust":5.87},
//    "rain":{"1h":4.86},"clouds":{"all":100},"dt":1662387487,"sys":{"country":"RU","sunrise":1662345117,"sunset":1662394246},"timezone":10800,"id":501183,"name":"Ростов","cod":200}