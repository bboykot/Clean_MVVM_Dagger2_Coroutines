package com.bboykot.weather.data.db

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface CitiesDao {

    @Query("SELECT * FROM CITIES")
    fun getCitiesList(): List<CitiesEntity>

    @Query("UPDATE cities SET isDefault = 0 WHERE isDefault = 1 ")
    suspend fun removeCurrentDefaultFlag()

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertCity(city: CitiesEntity)

}