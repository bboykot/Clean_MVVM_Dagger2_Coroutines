package com.bboykot.weather.app.di.module

import android.content.Context
import androidx.room.Room
import com.bboykot.weather.data.db.CitiesDatabase
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class DatabaseModule {

    @Singleton
    @Provides
    fun provideCitiesDatabase(context: Context): CitiesDatabase{
        return Room.databaseBuilder(context, CitiesDatabase::class.java, "cities").build()
    }
}