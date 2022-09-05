package com.bboykot.weather.app

import android.app.Application
import com.bboykot.weather.app.di.component.AppComponent
import com.bboykot.weather.app.di.component.DaggerAppComponent

class App: Application() {
    lateinit var appComponent: AppComponent

    override fun onCreate() {
        super.onCreate()

        appComponent = DaggerAppComponent.create()
    }
}