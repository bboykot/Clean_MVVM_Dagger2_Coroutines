package com.bboykot.weather.data.db

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "cities")
data class CitiesEntity(
    @PrimaryKey(autoGenerate = false)
    val id: Long,
    val city: String,
    val isDefault: Boolean
)
