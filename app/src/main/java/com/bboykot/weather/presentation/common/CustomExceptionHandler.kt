package com.bboykot.weather.presentation.common

import android.content.Context
import androidx.lifecycle.MutableLiveData
import com.bboykot.weather.R
import com.bboykot.weather.domain.models.Resource
import kotlinx.coroutines.CoroutineExceptionHandler
import javax.inject.Inject

class CustomExceptionHandler @Inject constructor(
    private val context: Context,
) {
    fun <T> getHandler(liveData: MutableLiveData<Resource<T>>): CoroutineExceptionHandler {
        return CoroutineExceptionHandler { _, throwable ->
            val errorText = throwable.message.orEmpty()
            when {
                errorText.contains(ERROR_CODE_BAD_REQUEST) -> context.getString(R.string.exception_enter_city)
                errorText.contains(ERROR_CODE_NOT_FOUND) -> context.getString(R.string.exception_wrong_city)
                errorText.contains(ERROR_CONNECTION) -> context.getString(R.string.exception_connection_error)
                else -> context.getString(R.string.exception_unknown_error)
            }.let { liveData.value = Resource.Failure(it) }
        }
    }

    companion object{
        private const val ERROR_CODE_BAD_REQUEST = "400"
        private const val ERROR_CODE_NOT_FOUND = "404"
        private const val ERROR_CONNECTION = "failed to connect"
    }
}