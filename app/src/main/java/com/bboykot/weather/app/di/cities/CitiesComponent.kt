package com.bboykot.weather.app.di.cities

import com.bboykot.weather.presentation.cities.CitiesFragment
import dagger.Subcomponent

@Subcomponent(modules = [CitiesModule::class])
interface CitiesComponent {

    fun injectCitiesFragment(fragment: CitiesFragment)
}