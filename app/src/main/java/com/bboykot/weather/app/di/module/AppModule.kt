package com.bboykot.weather.app.di.module

import android.os.Bundle
import androidx.lifecycle.ViewModel
import com.bboykot.weather.app.di.component.ViewModelKey
import com.bboykot.weather.domain.repository.ForecastRepository
import com.bboykot.weather.domain.usecases.GetDayForecastUseCase
import com.bboykot.weather.presentation.common.CustomExceptionHandler
import com.bboykot.weather.presentation.day.DayForecastViewModel
import dagger.Module
import dagger.Provides
import dagger.multibindings.IntoMap

@Module
class AppModule {

    @IntoMap
    @ViewModelKey(DayForecastViewModel::class)
    @Provides
    fun provideDayForecastViewModel(
        getDayForecastUseCase: GetDayForecastUseCase,
        exceptionHandler: CustomExceptionHandler,
        extras: Bundle,
    ): ViewModel {
        return DayForecastViewModel(getDayForecastUseCase, exceptionHandler, extras)
    }
}