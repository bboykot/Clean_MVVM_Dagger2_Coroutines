package com.bboykot.weather.domain.usecases

import androidx.lifecycle.LiveData
import com.bboykot.weather.domain.repository.ForecastRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetCitiesUseCase @Inject constructor(
    private val repository: ForecastRepository,
) {
    fun fetch(): Flow<List<String>> {
        return repository.getCitiesFromDb()
    }
}