package com.bboykot.weather.presentation.cities

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.bboykot.weather.domain.models.CurrentForecast
import com.bboykot.weather.domain.models.Resource
import com.bboykot.weather.domain.usecases.DeleteCityUseCase
import com.bboykot.weather.domain.usecases.GetCitiesUseCase
import com.bboykot.weather.domain.usecases.GetCurrentForecastUseCase
import com.bboykot.weather.presentation.common.CustomExceptionHandler
import kotlinx.coroutines.launch

class CitiesViewModel(
    private val getCitiesUseCase: GetCitiesUseCase,
    private val customExceptionHandler: CustomExceptionHandler,
    private val getCurrentForecastUseCase: GetCurrentForecastUseCase,
    private val deleteCityUseCase: DeleteCityUseCase,
) : ViewModel() {

    val citiesList = getCitiesUseCase.fetch()

    private val _result: MutableLiveData<Resource<List<CurrentForecast>>> = MutableLiveData()
    val result: LiveData<Resource<List<CurrentForecast>>> get() = _result

    fun getCitiesForecasts() {
        viewModelScope.launch(customExceptionHandler.getHandler(_result)) {
            val cities = citiesList.value.orEmpty()
            val citiesForecasts = mutableListOf<CurrentForecast>()
            _result.value = Resource.Loading()

            if (cities.isNotEmpty()) {
                cities.forEach { city ->
                    citiesForecasts.add(getCurrentForecastUseCase.fetch(city))
                }
                _result.value = Resource.Success(citiesForecasts)
            }
            else _result.value = Resource.Failure("Here is no saved cities. Join Search screen? find and save city")
        }
    }
    
    fun deleteCity(city: CurrentForecast){
        viewModelScope.launch { deleteCityUseCase.fetch(city) }
    }
}