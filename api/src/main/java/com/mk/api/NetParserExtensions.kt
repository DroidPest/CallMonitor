package com.mk.api

import com.mk.networking.NetRequestStatus

fun <T : Any, R : Any> NetRequestStatus<T>.validate(validator: (T) -> R): ResponseState<R> {
    return runCatching {
        when (this) {
            is NetRequestStatus.Success -> {
                try {
                    val validated = validator(value)
                    ResponseState.Success(validated)
                } catch (exception: ParsingException) {
                    ResponseState.Error(exception)
                }
            }
            is NetRequestStatus.Pending -> ResponseState.Loading
            is NetRequestStatus.Error -> ResponseState.Error(error)
        }
    }.getOrElse { ResponseState.Error(it) }
}
