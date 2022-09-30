package com.bboykot.weather.app.di.home

import com.bboykot.weather.presentation.home.HomeFragment
import dagger.Subcomponent

@Subcomponent(modules = [HomeModule::class])
interface HomeComponent {
    fun injectHomeFragment(fragment: HomeFragment)
}