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
import com.bboykot.weather.presentation.common.NotificationHelper
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch

class HomeViewModel(
    private val getCurrentForecastUseCase: GetCurrentForecastUseCase,
    private val getDefaultCityUseCase: GetDefaultCityUseCase,
    private val customExceptionHandler: CustomExceptionHandler,
    private val notificationHelper: NotificationHelper,
) : ViewModel() {

    private val _result = MutableLiveData<Resource<CurrentForecast>>()
    val result: LiveData<Resource<CurrentForecast>> get() = _result

    private var _defaultCity: String? = ""
    val defaultCity get() = _defaultCity.orEmpty()

    init {
        getDefaultCityUseCase.fetch()
            .onEach {
                loadForecast(it)
                _defaultCity = it
            }
            .launchIn(viewModelScope)
    }

    private fun loadForecast(city: String?) {
        viewModelScope.launch(customExceptionHandler.getHandler(_result)) {
            _result.value = Resource.Loading()

            if (city != null) {
                val forecast = getCurrentForecastUseCase.fetch(city)
                _result.value = Resource.Success(forecast)
            } else _result.value = Resource.Failure(notificationHelper.noDefaultCity)
        }
    }
}