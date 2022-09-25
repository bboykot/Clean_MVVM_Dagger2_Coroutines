package com.bboykot.weather.app.di.component

import android.content.Context
import com.bboykot.weather.app.di.component.day.DayForecastComponent
import com.bboykot.weather.app.di.module.AppModule
import com.bboykot.weather.app.di.module.DatabaseModule
import com.bboykot.weather.app.di.module.NetworkModule
import com.bboykot.weather.presentation.day.DayForecastFragment
import dagger.BindsInstance
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = [AppModule::class, NetworkModule::class, DatabaseModule::class])
interface AppComponent {

    @Component.Builder
    interface AppCompBuilder{
        fun buildAppComp(): AppComponent

        @BindsInstance
        fun context(context: Context) : AppCompBuilder
    }

    fun getSearchComponent(): SearchComponent
    fun getHomeComponent(): HomeComponent
    fun getCitiesComponent(): CitiesComponent
    fun getDayForecastComponent(): DayForecastComponent.ForecastCompBuilder
}