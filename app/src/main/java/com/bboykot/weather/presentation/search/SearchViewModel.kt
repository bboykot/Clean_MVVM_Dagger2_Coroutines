package com.bboykot.weather.presentation.search

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.bboykot.weather.domain.models.CurrentForecast
import com.bboykot.weather.domain.models.Resource
import com.bboykot.weather.domain.usecases.GetCurrentForecastUseCase
import com.bboykot.weather.domain.usecases.RemoveCurrentDefaultFlagUseCase
import com.bboykot.weather.domain.usecases.SaveCityUseCase
import com.bboykot.weather.presentation.common.CustomExceptionHandler
import kotlinx.coroutines.launch

class SearchViewModel(
    private val getCurrentForecastUseCase: GetCurrentForecastUseCase,
    private val saveCityUseCase: SaveCityUseCase,
    private val removeCurrentDefaultFlagUseCase: RemoveCurrentDefaultFlagUseCase,
    private val customExceptionHandler: CustomExceptionHandler,
) : ViewModel() {

    private val _result = MutableLiveData<Resource<CurrentForecast>>()
    val result: LiveData<Resource<CurrentForecast>> get() = _result

    fun startSearch(city: String) {
        viewModelScope.launch(customExceptionHandler.getHandler(_result)) {
            _result.value = Resource.Loading()
            val result = getCurrentForecastUseCase.getCurrentForecastForCity(city)
            _result.value = Resource.Success(result)
        }
    }

    fun saveCity() {
        val forecast = _result.value
        if (forecast is Resource.Success<CurrentForecast>)
            viewModelScope.launch { saveCityUseCase.saveCity(forecast.data, false) }
    }

    fun saveCityAsDefault() {
        val forecast = _result.value
        if (forecast is Resource.Success<CurrentForecast>) {
            viewModelScope.launch {
                removeCurrentDefaultFlagUseCase.removeCurrentDefaultFlag()
                saveCityUseCase.saveCity(forecast.data, true)
            }
        }
    }
}