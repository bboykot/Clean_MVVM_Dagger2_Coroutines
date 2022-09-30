package com.bboykot.weather.app.di.search

import com.bboykot.weather.presentation.search.SearchFragment
import dagger.Subcomponent

@Subcomponent(modules = [SearchModule::class])
interface SearchComponent {

    fun injectSearchFragment(fragment: SearchFragment)
}