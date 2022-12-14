package com.bboykot.weather.data.db

import androidx.room.*
import kotlinx.coroutines.flow.Flow

@Dao
interface CitiesDao {

    @Query("SELECT city FROM CITIES")
    fun getCitiesList(): Flow<List<String>>

    @Query("SELECT city FROM cities WHERE isDefault = 1")
    fun getDefaultCity(): Flow<String>

    @Query("UPDATE cities SET isDefault = 0 WHERE isDefault = 1 ")
    suspend fun removeCurrentDefaultFlag()

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertCity(city: CitiesEntity)

    @Delete
    suspend fun deleteCity(city: CitiesEntity)
}