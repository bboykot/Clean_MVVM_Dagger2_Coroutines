package com.bboykot.weather.app.di.module

import androidx.lifecycle.ViewModel
import com.bboykot.weather.app.di.component.ViewModelKey
import com.bboykot.weather.domain.usecases.GetCurrentForecastUseCase
import com.bboykot.weather.domain.usecases.GetDefaultCityUseCase
import com.bboykot.weather.presentation.home.HomeViewModel
import dagger.Module
import dagger.Provides
import dagger.multibindings.IntoMap

@Module
class HomeModule {

    @IntoMap
    @ViewModelKey(HomeViewModel::class)
    @Provides
    fun provideHomeViewModel(
        getCurrentForecastUseCase: GetCurrentForecastUseCase,
        getDefaultCityUseCase: GetDefaultCityUseCase
    ): ViewModel {
       return HomeViewModel(getCurrentForecastUseCase, getDefaultCityUseCase)
    }
}