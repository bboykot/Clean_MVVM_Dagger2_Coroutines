package com.bboykot.weather.app.di.module

import androidx.lifecycle.ViewModel
import com.bboykot.weather.app.di.component.ViewModelKey
import com.bboykot.weather.presentation.cities.CitiesViewModel
import dagger.Module
import dagger.Provides
import dagger.multibindings.IntoMap

@Module
class CitiesModule {

    @IntoMap
    @ViewModelKey(CitiesViewModel::class)
    @Provides
    fun provideViewModel(): ViewModel {
        return CitiesViewModel()
    }
}