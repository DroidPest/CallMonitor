package com.mk.api

sealed class ResponseState<out T> {
    data class Success<T>(val item: T) : ResponseState<T>()
    data class Error(val throwable: Throwable) : ResponseState<Nothing>()
    data object Loading : ResponseState<Nothing>()

    fun getOrNull(): T? = when (this) {
        is Success -> item
        else -> null
    }
}
