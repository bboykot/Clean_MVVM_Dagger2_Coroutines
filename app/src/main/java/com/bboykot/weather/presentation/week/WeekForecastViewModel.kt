package com.bboykot.weather.presentation.week

import android.os.Bundle
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.bboykot.weather.domain.models.DailyForecast
import com.bboykot.weather.domain.models.HourForecast
import com.bboykot.weather.domain.models.Resource
import com.bboykot.weather.domain.repository.ForecastRepository
import com.bboykot.weather.domain.usecases.GetWeekForecastUseCase
import com.bboykot.weather.presentation.common.CustomExceptionHandler
import com.bboykot.weather.presentation.day.DayForecastFragment
import kotlinx.coroutines.launch

class WeekForecastViewModel(
    private val getWeekForecastUseCase: GetWeekForecastUseCase,
    private val exceptionHandler: CustomExceptionHandler,
    private val extras: Bundle?,
): ViewModel() {

    private val _result = MutableLiveData<Resource<List<DailyForecast>>>()
    val result: LiveData<Resource<List<DailyForecast>>> get() = _result

    init {
        val city = extras?.getString(DayForecastFragment.ARGUMENT_CITY_KEY)
        loadForecast(city.orEmpty())
    }
    private fun loadForecast(city: String){
        viewModelScope.launch(exceptionHandler.getHandler(_result)) {
            _result.value = Resource.Loading()

            val forecast = getWeekForecastUseCase.fetch(city)
            _result.value = Resource.Success(forecast)
        }
    }
}