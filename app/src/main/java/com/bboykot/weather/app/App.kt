package com.bboykot.weather.app

import android.app.Application
import com.bboykot.weather.app.di.AppComponent
import com.bboykot.weather.app.di.DaggerAppComponent

class App : Application() {
    lateinit var appComponent: AppComponent

    override fun onCreate() {
        super.onCreate()

        appComponent = DaggerAppComponent.builder().context(this).buildAppComp()
    }
}