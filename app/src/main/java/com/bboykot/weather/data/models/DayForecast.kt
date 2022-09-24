package com.bboykot.weather.data.models

import com.google.gson.annotations.SerializedName

data class DayForecast(
//    val city: City,
    @SerializedName("list")
    val dayForecast: List<PartDayForecast>
)

data class PartDayForecast(
    @SerializedName("dt")
    val time: Long,
    val main: Main,
    val weather: List<Weather>,
    val wind: Wind,
)

//data class City(
//    @SerializedName("id")
//    val id: Long,
//    @SerializedName("name")
//    val city: String
//)


//{"cod":"200","message":0,"cnt":8,"list":[{"dt":1663880400,"main":{"temp":10.27,"feels_like":9.73,"temp_min":9.82,"temp_max":10.27,"pressure":1017,"sea_level":1017,
//    "grnd_level":1004,"humidity":91,"temp_kf":0.45},"weather":[{"id":804,"main":"Clouds","description":"пасмурно","icon":"04n"}],"clouds":{"all":100},
//    "wind":{"speed":3.02,"deg":24,"gust":5.44},"visibility":10000,"pop":0,"sys":{"pod":"n"},"dt_txt":"2022-09-22 21:00:00"},{"dt":1663891200,
//    "main":{"temp":9.71,"feels_like":8.37,"temp_min":9.31,"temp_max":9.71,"pressure":1016,"sea_level":1016,"grnd_level":1004,"humidity":92,"temp_kf":0.4},
//    "weather":[{"id":804,"main":"Clouds","description":"пасмурно","icon":"04n"}],"clouds":{"all":100},"wind":{"speed":2.67,"deg":23,"gust":5.31},
//    "visibility":10000,"pop":0,"sys":{"pod":"n"},"dt_txt":"2022-09-23 00:00:00"},{"dt":1663902000,
//    "main":{"temp":8.84,"feels_like":7.36,"temp_min":8.84,"temp_max":8.84,"pressure":1016,"sea_level":1016,"grnd_level":1003,"humidity":92,"temp_kf":0},
//    "weather":[{"id":804,"main":"Clouds","description":"пасмурно","icon":"04n"}],"clouds":{"all":100},"wind":{"speed":2.63,"deg":16,"gust":5.31},"visibility":10000,"pop":0,
//    "sys":{"pod":"n"},"dt_txt":"2022-09-23 03:00:00"},{"dt":1663912800,"main":{"temp":10.84,"feels_like":10.09,"temp_min":10.84,"temp_max":10.84,"pressure":1015,"sea_level":
//    1015,"grnd_level":1003,"humidity":81,"temp_kf":0},"weather":[{"id":804,"main":"Clouds","description":"пасмурно","icon":"04d"}],"clouds":{"all":92},
//    "wind":{"speed":3.15,"deg":27,"gust":4},"visibility":10000,"pop":0,"sys":{"pod":"d"},"dt_txt":"2022-09-23 06:00:00"},{"dt":1663923600,
//    "main":{"temp":14.28,"feels_like":13.43,"temp_min":14.28,"temp_max":14.28,"pressure":1015,"sea_level":1015,"grnd_level":1003,"humidity":64,"temp_kf":0},
//    "weather":[{"id":803,"main":"Clouds","description":"облачно с прояснениями","icon":"04d"}],"clouds":{"all":71},"wind":{"speed":3.35,"deg":20,"gust":3.79},
//    "visibility":10000,"pop":0,"sys":{"pod":"d"},"dt_txt":"2022-09-23 09:00:00"},{"dt":1663934400,"main":{"temp":14.82,"feels_like":13.95,"temp_min":14.82,
//    "temp_max":14.82,"pressure":1014,"sea_level":1014,"grnd_level":1002,"humidity":61,"temp_kf":0},"weather":[{"id":500,"main":"Rain","description":"небольшой дождь","icon":"10d"}],
//    "clouds":{"all":85},"wind":{"speed":4.83,"deg":35,"gust":5.2},"visibility":10000,"pop":0.2,"rain":{"3h":0.13},"sys":{"pod":"d"},"dt_txt":"2022-09-23 12:00:00"}
//    ,{"dt":1663945200,"main":{"temp":11.39,"feels_like":10.6,"temp_min":11.39,"temp_max":11.39,"pressure":1014,"sea_level":1014,"grnd_level":1002,"humidity":77,"temp_kf":0},
//    "weather":[{"id":804,"main":"Clouds","description":"пасмурно","icon":"04d"}],"clouds":{"all":100},"wind":{"speed":4.39,"deg":20,"gust":8.09},
//    "visibility":10000,"pop":0,"sys":{"pod":"d"},"dt_txt":"2022-09-23 15:00:00"},{"dt":1663956000,
//    "main":{"temp":10.09,"feels_like":9.32,"temp_min":10.09,"temp_max":10.09,"pressure":1013,"sea_level":1013,"grnd_level":1001,"humidity":83,"temp_kf":0},
//    "weather":[{"id":804,"main":"Clouds","description":"пасмурно","icon":"04n"}],"clouds":{"all":100},"wind":{"speed":4.44,"deg":35,"gust":9.44},"visibility":10000,"pop":0,
//    "sys":{"pod":"n"},"dt_txt":"2022-09-23 18:00:00"}],"city":{"id":501183,"name":"Ростов","coord":{"lat":57.1914,"lon":39.4139},"country":"RU","population":33803,"timezone":10800,
//    "sunrise":1663816003,"sunset":1663860222}}