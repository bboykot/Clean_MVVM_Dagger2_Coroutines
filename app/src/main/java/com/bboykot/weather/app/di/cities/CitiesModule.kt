package com.bboykot.weather.app.di.cities

import androidx.lifecycle.ViewModel
import com.bboykot.weather.app.di.ViewModelKey
import com.bboykot.weather.domain.usecases.DeleteCityUseCase
import com.bboykot.weather.domain.usecases.GetCitiesUseCase
import com.bboykot.weather.domain.usecases.GetCurrentForecastUseCase
import com.bboykot.weather.presentation.cities.CitiesViewModel
import com.bboykot.weather.presentation.common.CustomExceptionHandler
import com.bboykot.weather.presentation.common.NotificationHelper
import dagger.Module
import dagger.Provides
import dagger.multibindings.IntoMap

@Module
class CitiesModule {

    @IntoMap
    @ViewModelKey(CitiesViewModel::class)
    @Provides
    fun provideViewModel(
        getCitiesUseCase: GetCitiesUseCase,
        customExceptionHandler: CustomExceptionHandler,
        getCurrentForecastUseCase: GetCurrentForecastUseCase,
        deleteCityUseCase: DeleteCityUseCase,
        notificationHelper: NotificationHelper,
    ): ViewModel {
        return CitiesViewModel(
            getCitiesUseCase,
            customExceptionHandler,
            getCurrentForecastUseCase,
            deleteCityUseCase,
            notificationHelper
        )
    }
}