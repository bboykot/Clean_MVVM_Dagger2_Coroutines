package com.bboykot.weather.domain.models

sealed class Resource<out T> {

    data class Success<out R>(val data: R) : Resource<R>()
    data class Failure(val message: String?) : Resource<Nothing>()
    class Loading() : Resource<Nothing>()
}
