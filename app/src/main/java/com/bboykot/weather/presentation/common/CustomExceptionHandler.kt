package com.bboykot.weather.presentation.common

import androidx.lifecycle.MutableLiveData
import com.bboykot.weather.domain.models.Resource
import kotlinx.coroutines.CoroutineExceptionHandler
import javax.inject.Inject

class CustomExceptionHandler @Inject constructor() {
    fun <T> getHandler(liveData: MutableLiveData<Resource<T>>): CoroutineExceptionHandler {
        return CoroutineExceptionHandler { coroutineContext, throwable ->
            liveData.value = Resource.Failure(throwable.message)
        }
    }
}