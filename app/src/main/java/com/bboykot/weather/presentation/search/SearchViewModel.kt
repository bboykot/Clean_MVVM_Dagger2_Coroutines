package com.bboykot.weather.presentation.search

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.bboykot.weather.domain.models.CurrentForecast
import com.bboykot.weather.domain.usecases.GetCurrentForecastUseCase
import com.bboykot.weather.domain.usecases.SaveCityUseCase
import kotlinx.coroutines.launch

class SearchViewModel(
    private val getCurrentForecastUseCase: GetCurrentForecastUseCase,
    private val saveCityUseCase: SaveCityUseCase,
): ViewModel() {

    private val _searchResult = MutableLiveData<CurrentForecast>()
    val searchResult: LiveData<CurrentForecast> get() = _searchResult

    private val requestErrorPrivate = MutableLiveData<String>()
    val requestError get() = requestErrorPrivate

    private val progressVisibilityPrivate = MutableLiveData<Boolean>()
    val progressVisibility get() = progressVisibilityPrivate

    fun startSearch(city: String){
        progressVisibilityPrivate.value = true

        viewModelScope.launch {
            try {
                val result = getCurrentForecastUseCase.getCurrentForecastForCity(city)
                _searchResult.value = result
            }
            catch (error: Exception) {
                requestErrorPrivate.value = error.toString()
            }
            progressVisibilityPrivate.value = false
        }
    }

    fun saveCity(){
        viewModelScope.launch { saveCityUseCase.saveCity(_searchResult.value, false) }
    }

    fun saveCityAsDefault(){
        viewModelScope.launch { saveCityUseCase.saveCity(_searchResult.value, true) }
    }
}