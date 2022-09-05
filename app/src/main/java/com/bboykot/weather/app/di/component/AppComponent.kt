package com.bboykot.weather.app.di.component

import com.bboykot.weather.app.di.module.AppModule
import com.bboykot.weather.app.di.module.NetworkModule
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = [AppModule::class, NetworkModule::class])
interface AppComponent {

    fun getSearchComponent(): SearchComponent
}