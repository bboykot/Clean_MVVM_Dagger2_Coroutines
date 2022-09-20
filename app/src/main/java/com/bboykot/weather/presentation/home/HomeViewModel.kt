package com.bboykot.weather.presentation.home

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.bboykot.weather.domain.models.CurrentForecast
import com.bboykot.weather.domain.models.Resource
import com.bboykot.weather.domain.usecases.GetCurrentForecastUseCase
import com.bboykot.weather.domain.usecases.GetDefaultCityUseCase
import com.bboykot.weather.presentation.common.CustomExceptionHandler
import kotlinx.coroutines.launch

class HomeViewModel(
    private val getCurrentForecastUseCase: GetCurrentForecastUseCase,
    private val getDefaultCityUseCase: GetDefaultCityUseCase,
    private val customExceptionHandler: CustomExceptionHandler,
) : ViewModel() {

    private val _result = MutableLiveData<Resource<CurrentForecast>>()
    val result: LiveData<Resource<CurrentForecast>> get() = _result

    val defaultCity: LiveData<String?> = getDefaultCityUseCase.fetch()

    fun loadForecast() {
        viewModelScope.launch(customExceptionHandler.getHandler(_result)) {
            _result.value = Resource.Loading()

            val city:String? = defaultCity.value

            if (city != null) {
                val forecast = getCurrentForecastUseCase.fetch(city)
                _result.value = Resource.Success(forecast)
            } else _result.value = Resource.Failure("Default city is not chosen. Join Search screen, find your city and set him as default")
        }
    }
}