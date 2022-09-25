package com.bboykot.weather.presentation.week

import android.os.Bundle
import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.bboykot.weather.domain.repository.ForecastRepository
import com.bboykot.weather.domain.usecases.GetWeekForecastUseCase
import kotlinx.coroutines.launch

class WeekForecastViewModel(
    private val getWeekForecastUseCase: GetWeekForecastUseCase,
    private val extras: Bundle?,
): ViewModel() {

    init {
        Log.i("XXX", "wee viewmodel: ")
        viewModelScope.launch {
            val data = getWeekForecastUseCase.fetch("Rostov-on-Don")
            Log.i("XXX", "Week: $data ")
        }
    }
}