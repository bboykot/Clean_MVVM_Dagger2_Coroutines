package com.bboykot.weather.app.di.home

import androidx.lifecycle.ViewModel
import com.bboykot.weather.app.di.ViewModelKey
import com.bboykot.weather.domain.usecases.GetCurrentForecastUseCase
import com.bboykot.weather.domain.usecases.GetDefaultCityUseCase
import com.bboykot.weather.presentation.common.CustomExceptionHandler
import com.bboykot.weather.presentation.common.NotificationHelper
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
        getDefaultCityUseCase: GetDefaultCityUseCase,
        customExceptionHandler: CustomExceptionHandler,
        notificationHelper: NotificationHelper
    ): ViewModel {
        return HomeViewModel(
            getCurrentForecastUseCase,
            getDefaultCityUseCase,
            customExceptionHandler,
            notificationHelper
        )
    }
}