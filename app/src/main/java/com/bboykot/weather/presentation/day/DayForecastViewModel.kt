package com.bboykot.weather.presentation.day

import android.os.Bundle
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.bboykot.weather.domain.models.HourForecast
import com.bboykot.weather.domain.models.Resource
import com.bboykot.weather.domain.repository.ForecastRepository
import com.bboykot.weather.domain.usecases.GetDayForecastUseCase
import com.bboykot.weather.presentation.common.CustomExceptionHandler
import kotlinx.coroutines.launch
import javax.inject.Inject

class DayForecastViewModel(
    private val getDayForecastUseCase: GetDayForecastUseCase,
    private val exceptionHandler: CustomExceptionHandler,
    private val extras: Bundle?,
): ViewModel() {

    private val _result = MutableLiveData<Resource<List<HourForecast>>>()
    val result: LiveData<Resource<List<HourForecast>>> get() = _result

    init {
        val city = extras?.getString(DayForecastFragment.ARGUMENT_CITY_KEY)
        loadForecast(city.orEmpty())
    }
    private fun loadForecast(city: String){
        viewModelScope.launch(exceptionHandler.getHandler(_result)) {
            val forecast = getDayForecastUseCase.fetch(city)
            _result.value = Resource.Success(forecast)
        }
    }
}