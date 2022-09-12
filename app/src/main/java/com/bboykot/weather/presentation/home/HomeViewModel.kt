package com.bboykot.weather.presentation.home

import android.util.Log
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
    val result get() = _result

    private val _progressVisibility = MutableLiveData<Boolean>()
    val progressVisibility get() = _progressVisibility

    init {
        loadForecast()
    }

    private fun loadForecast() {
        viewModelScope.launch {
            _progressVisibility.value = true

            val defaultCity = getDefaultCityUseCase.fetch()

            try {
                if (defaultCity != null) {
                    val forecast = getCurrentForecastUseCase.getCurrentForecastForCity(defaultCity)
                    _result.value = Resource.Success(forecast)
                } else throw IllegalArgumentException()

            } catch (error: java.lang.IllegalArgumentException) {
                _result.value = Resource.Failure("Default city is not chosen")

            } catch (error: Exception) {
                Log.i("XXX", "loadForecast: ")
                _result.value = Resource.Failure(error.toString())
            }
            _progressVisibility.value = false
        }
    }
}