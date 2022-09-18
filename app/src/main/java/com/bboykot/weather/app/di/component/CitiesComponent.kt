package com.bboykot.weather.app.di.component

import com.bboykot.weather.app.di.module.CitiesModule
import com.bboykot.weather.presentation.cities.CitiesFragment
import com.bboykot.weather.presentation.cities.CitiesViewModel
import dagger.Subcomponent

@Subcomponent(modules = [CitiesModule::class])
interface CitiesComponent {

    fun injectCitiesFragment(fragment: CitiesFragment)
}