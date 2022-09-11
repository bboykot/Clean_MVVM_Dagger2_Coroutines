package com.bboykot.weather.presentation.search

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.bboykot.weather.domain.models.CurrentForecast
import com.bboykot.weather.domain.models.Resource
import com.bboykot.weather.domain.usecases.GetCurrentForecastUseCase
import com.bboykot.weather.domain.usecases.RemoveCurrentDefaultFlagUseCase
import com.bboykot.weather.domain.usecases.SaveCityUseCase
import kotlinx.coroutines.launch

class SearchViewModel(
    private val getCurrentForecastUseCase: GetCurrentForecastUseCase,
    private val saveCityUseCase: SaveCityUseCase,
    private val removeCurrentDefaultFlagUseCase: RemoveCurrentDefaultFlagUseCase
): ViewModel() {

    private val _result = MutableLiveData<Resource<CurrentForecast>>()
    val result get() = _result

    private val progressVisibilityPrivate = MutableLiveData<Boolean>()
    val progressVisibility get() = progressVisibilityPrivate

    fun startSearch(city: String){
        progressVisibilityPrivate.value = true

        viewModelScope.launch {
            try {
                val result = getCurrentForecastUseCase.getCurrentForecastForCity(city)
                _result.value = Resource.Success(result)
            }
            catch (error: Exception) {
                _result.value = Resource.Failure(error.toString())
            }
            progressVisibilityPrivate.value = false
        }
    }

    fun saveCity(){
        val forecast = _result.value
        if (forecast is Resource.Success<CurrentForecast>)
        viewModelScope.launch { saveCityUseCase.saveCity( forecast.data, false) }
    }

    fun saveCityAsDefault(){
        val forecast = _result.value
        if (forecast is Resource.Success<CurrentForecast>) {
            viewModelScope.launch {
                removeCurrentDefaultFlagUseCase.removeCurrentDefaultFlag()
                saveCityUseCase.saveCity(forecast.data, true)
            }
        }
    }
}