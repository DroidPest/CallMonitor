package com.mk.networking

import retrofit2.HttpException
import kotlin.contracts.ExperimentalContracts
import kotlin.contracts.contract
import kotlin.reflect.typeOf
import retrofit2.Response as RetrofitResponse

sealed class NetRequestStatus<out T : Any> {
    abstract val value: T?

    data class Success<T : Any>(
        override val value: T,
    ) : NetRequestStatus<T>()

    data class Error<out T : Any>(
        val error: Throwable,
        override val value: T? = null,
    ) : NetRequestStatus<T>()

    data class Pending<out T : Any>(
        override val value: T? = null,
    ) : NetRequestStatus<T>()

    companion object {
        fun <T : Any> success(
            value: T,
        ): NetRequestStatus<T> = Success(value)

        fun <T : Any> error(
            error: Throwable,
            value: T? = null,
        ): NetRequestStatus<T> = Error(error, value)

        fun <T : Any> pending(
            value: T? = null,
        ): NetRequestStatus<T> = Pending(value)

        inline fun <reified T : Any> fromResponse(
            response: RetrofitResponse<T>,
        ): NetRequestStatus<T> =
            kotlin.runCatching { success(response.getOrThrow()) }
                .getOrElse { error(it) }
    }

    data class InitialValue(val value: Any)
}

fun <T : Any> NetRequestStatus<T>.toSuccess(value: T): NetRequestStatus<T> {
    return when (this) {
        is NetRequestStatus.Success -> NetRequestStatus.Success(value)
        is NetRequestStatus.Error -> NetRequestStatus.Success(value)
        is NetRequestStatus.Pending -> NetRequestStatus.Success(value)
    }
}

fun <T : Any> NetRequestStatus<T>.toError(error: Throwable): NetRequestStatus<T> {
    return when (this) {
        is NetRequestStatus.Success -> NetRequestStatus.Error<T>(error, value as T?)
        is NetRequestStatus.Error -> NetRequestStatus.Error<T>(error, value)
        is NetRequestStatus.Pending -> NetRequestStatus.Error<T>(error, value)
    }
}

@OptIn(ExperimentalContracts::class)
fun <T : Any> NetRequestStatus<T>.isSuccessful(): Boolean {
    contract { returns(true) implies (this@isSuccessful is NetRequestStatus.Success) }
    return (this is NetRequestStatus.Success)
}

inline fun <reified T> RetrofitResponse<T>.getOrThrow(): T =
    when {
        typeOf<T>().isMarkedNullable && isSuccessful -> body() as T
        isSuccessful -> checkNotNull(body()) {
            "Response body type ${T::class.java.simpleName} is non-null but returned null"
        }

        else -> throw HttpException(this)
    }
