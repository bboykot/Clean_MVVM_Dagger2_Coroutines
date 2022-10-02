package com.bboykot.weather.app.di

import android.content.Context
import com.bboykot.weather.app.di.cities.CitiesComponent
import com.bboykot.weather.app.di.forecast.ForecastComponent
import com.bboykot.weather.app.di.home.HomeComponent
import com.bboykot.weather.app.di.search.SearchComponent
import dagger.BindsInstance
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = [AppModule::class, NetworkModule::class, DatabaseModule::class])
interface AppComponent {

    @Component.Builder
    interface AppCompBuilder {
        fun buildAppComp(): AppComponent

        @BindsInstance
        fun context(context: Context): AppCompBuilder
    }

    fun getSearchComponent(): SearchComponent
    fun getHomeComponent(): HomeComponent
    fun getCitiesComponent(): CitiesComponent
    fun getForecastComponent(): ForecastComponent.ForecastCompBuilder
}