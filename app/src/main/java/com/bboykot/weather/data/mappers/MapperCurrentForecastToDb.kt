package com.bboykot.weather.data.mappers

import com.bboykot.weather.data.db.CitiesEntity
import com.bboykot.weather.domain.models.CurrentForecast
import javax.inject.Inject

class MapperCurrentForecastToDb @Inject constructor() : Mapper<Pair<CurrentForecast, Boolean>, CitiesEntity>() {
    override fun map(from: Pair<CurrentForecast, Boolean>): CitiesEntity {
        return CitiesEntity(from.first.id, from.first.city, from.second)
    }
}