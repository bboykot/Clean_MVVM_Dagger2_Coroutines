package com.bboykot.weather.presentation.home

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.bboykot.weather.domain.models.CurrentForecast
import com.bboykot.weather.domain.models.Resource
import com.bboykot.weather.domain.usecases.GetCurrentForecastUseCase
import com.bboykot.weather.domain.usecases.GetDefaultCityUseCase
import kotlinx.coroutines.launch

class HomeViewModel(
    private val getCurrentForecastUseCase: GetCurrentForecastUseCase,
    private val getDefaultCityUseCase: GetDefaultCityUseCase,
) : ViewModel() {

    private val _result = MutableLiveData<Resource<CurrentForecast>>()
    val result: LiveData<Resource<CurrentForecast>> get() = _result

    private val _progressVisibility = MutableLiveData<Boolean>()
    val progressVisibility: LiveData<Boolean> get() = _progressVisibility

    val defaultCity: LiveData<String?> = getDefaultCityUseCase.fetch()

    fun loadForecast() {
        _result.value = Resource.Failure("")
        viewModelScope.launch {
            _progressVisibility.value = true

            val city:String? = defaultCity.value

            try {
                if (city != null) {
                    val forecast = getCurrentForecastUseCase.getCurrentForecastForCity(city)
                    _result.value = Resource.Success(forecast)
                } else throw IllegalArgumentException()
            } catch (error: java.lang.IllegalArgumentException) {
                _result.value =
                    Resource.Failure("Default city is not chosen. Join Search screen, find your city and set him as default")
            } catch (error: Exception) {
                _result.value = Resource.Failure(error.toString())
            }
            _progressVisibility.value = false
        }
    }
}