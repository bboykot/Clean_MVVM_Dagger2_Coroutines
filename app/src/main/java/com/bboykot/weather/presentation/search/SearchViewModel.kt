package com.bboykot.weather.presentation.search

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.bboykot.weather.data.remote.WeatherApiService
import kotlinx.coroutines.launch

class SearchViewModel(
    private val weatherApiService: WeatherApiService
): ViewModel() {

    private val _searchResult = MutableLiveData<String>()
    val searchResult: LiveData<String> get() = _searchResult

    fun startSearch(city: String){
        viewModelScope.launch {
            val result = weatherApiService.getCurrentForecast(city)
            _searchResult.value = result
        }
    }
}