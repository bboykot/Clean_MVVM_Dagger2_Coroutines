package com.bboykot.weather.data.db

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface CitiesDao {

    @Query("SELECT * FROM CITIES")
    fun getCitiesList(): List<CitiesEntity>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertCity(city: CitiesEntity)

}