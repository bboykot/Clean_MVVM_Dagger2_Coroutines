package com.bboykot.weather.app.di.module

import androidx.lifecycle.ViewModel
import com.bboykot.weather.app.di.component.ViewModelKey
import com.bboykot.weather.data.remote.WeatherApiService
import com.bboykot.weather.domain.usecases.GetCurrentForecastUseCase
import com.bboykot.weather.domain.usecases.SaveCityUseCase
import com.bboykot.weather.presentation.search.SearchViewModel
import dagger.Module
import dagger.Provides
import dagger.multibindings.IntoMap

@Module
class SearchModule {

    @IntoMap
    @ViewModelKey(SearchViewModel::class)
    @Provides
    fun provideSearchViewModel(getCurrentForecastUseCase: GetCurrentForecastUseCase, saveCityUseCase: SaveCityUseCase):ViewModel{
        return SearchViewModel(getCurrentForecastUseCase, saveCityUseCase)
    }
}