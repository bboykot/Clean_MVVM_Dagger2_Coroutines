package com.bboykot.weather.app.di.component

import com.bboykot.weather.app.di.module.HomeModule
import com.bboykot.weather.presentation.home.HomeFragment
import dagger.Subcomponent

@Subcomponent(modules = [HomeModule::class])
interface HomeComponent {
    fun injectHomeFragment(fragment: HomeFragment)
}