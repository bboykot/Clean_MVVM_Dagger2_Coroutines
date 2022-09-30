package com.bboykot.weather.app.di.search

import androidx.lifecycle.ViewModel
import com.bboykot.weather.app.di.ViewModelKey
import com.bboykot.weather.domain.usecases.GetCurrentForecastUseCase
import com.bboykot.weather.domain.usecases.RemoveCurrentDefaultFlagUseCase
import com.bboykot.weather.domain.usecases.SaveCityUseCase
import com.bboykot.weather.presentation.common.CustomExceptionHandler
import com.bboykot.weather.presentation.search.SearchViewModel
import dagger.Module
import dagger.Provides
import dagger.multibindings.IntoMap

@Module
class SearchModule {

    @IntoMap
    @ViewModelKey(SearchViewModel::class)
    @Provides
    fun provideSearchViewModel(
        getCurrentForecastUseCase: GetCurrentForecastUseCase,
        saveCityUseCase: SaveCityUseCase,
        removeCurrentDefaultFlagUseCase: RemoveCurrentDefaultFlagUseCase,
        customExceptionHandler: CustomExceptionHandler,
    ): ViewModel {
        return SearchViewModel(
            getCurrentForecastUseCase,
            saveCityUseCase,
            removeCurrentDefaultFlagUseCase,
            customExceptionHandler,
        )
    }
}