package com.mk.networking

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.CallAdapter
import retrofit2.Callback
import retrofit2.Converter
import retrofit2.HttpException
import retrofit2.Retrofit
import retrofit2.await
import java.lang.reflect.ParameterizedType
import java.lang.reflect.Type
import retrofit2.Response as RetrofitResponse

/**
 * Produces a Flow that must always follow this contract:
 * 1: The flow must immediately emit a single [NetRequestStatus.Pending] without dispatching.
 * 2: The flow must emit a single [NetRequestStatus.Success] OR a single [NetRequestStatus.Error].
 */
class NetRequestStatusFlowAdapter<Body : Any>(
    private val defaultAdapter: CallAdapter<Body, Call<Body>>,
) : CallAdapter<Body, Flow<NetRequestStatus<Body>>> {

    override fun responseType(): Type = defaultAdapter.responseType()

    override fun adapt(call: Call<Body>): Flow<NetRequestStatus<Body>> {
        return flow {
            val clone = call.clone()

            @Suppress("UNCHECKED_CAST")
            val initialValue = clone.request()
                .tag(NetRequestStatus.InitialValue::class.java)?.value as? Body

            val pending = NetRequestStatus.Pending(
                value = initialValue,
            )
            emit(pending)

            runCatching { defaultAdapter.adapt(clone).await() }
                .onSuccess { emit(pending.toSuccess(it)) }
                .onFailure { emit(pending.toError(it)) }
        }
    }
}

/**
 * A call adapter to insert before the default call adapter in order to ensure
 * a retrofit service method that returns a NetRequestStatus can never throw
 * an exception but will instead return a NetRequestStatus.
 */
class NetRequestStatusAdapter<Body : Any>(
    private val defaultAdapter: CallAdapter<NetRequestStatus<Body>, Call<NetRequestStatus<Body>>>,
) : CallAdapter<NetRequestStatus<Body>, Call<NetRequestStatus<Body>>> {

    override fun responseType(): Type = defaultAdapter.responseType()

    override fun adapt(call: Call<NetRequestStatus<Body>>): Call<NetRequestStatus<Body>> {
        @Suppress("UNCHECKED_CAST")
        val initialValue = call.request()
            .tag(NetRequestStatus.InitialValue::class.java)?.value as? Body

        val subCall = defaultAdapter.adapt(call)

        return object : Call<NetRequestStatus<Body>> by subCall {
            val wrappingCall = this

            override fun clone(): Call<NetRequestStatus<Body>> {
                return adapt(call.clone())
            }

            override fun execute(): RetrofitResponse<NetRequestStatus<Body>> {
                return runCatching { subCall.execute() }
                    .map { processResponse(it) }
                    .getOrElse {
                        RetrofitResponse.success(
                            NetRequestStatus.error(
                                it,
                                initialValue,
                            ),
                        )
                    }
            }

            override fun enqueue(callback: Callback<NetRequestStatus<Body>>) {
                subCall.enqueue(object : Callback<NetRequestStatus<Body>> {
                    override fun onResponse(
                        call: Call<NetRequestStatus<Body>>,
                        response: RetrofitResponse<NetRequestStatus<Body>>,
                    ) {
                        callback.onResponse(wrappingCall, processResponse(response))
                    }

                    override fun onFailure(call: Call<NetRequestStatus<Body>>, t: Throwable) {
                        callback.onResponse(
                            wrappingCall,
                            RetrofitResponse.success(
                                NetRequestStatus.error(t, initialValue),
                            ),
                        )
                    }
                })
            }

            fun processResponse(
                response: RetrofitResponse<NetRequestStatus<Body>>,
            ): RetrofitResponse<NetRequestStatus<Body>> {
                return runCatching {
                    if (!response.isSuccessful) {
                        throw HttpException(response)
                    }

                    when (val status = response.body()) {
                        is NetRequestStatus.Pending ->
                            unreachable(
                                "${NetRequestStatusTypeConverter::class.java} should never " +
                                    "produce pending: $response ${response.body()}",
                            )

                        is NetRequestStatus.Error ->
                            status.copy(value = initialValue)

                        is NetRequestStatus.Success ->
                            status.copy()

                        else -> {
                            @Suppress("MagicNumber")
                            when (response.code()) {
                                204, 205 -> {
                                    // Retrofit's okhttp call skips the type converter on 204 or 205 responses
                                    // Both are still in the Success range of response codes
                                    // Implicitly map to Unit and continue
                                    @Suppress("UNCHECKED_CAST")
                                    NetRequestStatus.success(Unit) as NetRequestStatus<Body>
                                }

                                else -> unreachable(
                                    "${NetRequestStatusTypeConverter::class.java} should have " +
                                        "thrown already: $response ${response.body()}",
                                )
                            }
                        }
                    }
                }
                    .recover { NetRequestStatus.error(it, initialValue) }
                    .map { RetrofitResponse.success(it) }
                    .getOrThrow()
            }
        }
    }

    @Throws(IllegalStateException::class)
    internal fun unreachable(message: String): Nothing =
        error("Unreachable: $message")
}

class NetRequestStatusCallAdapter {

    object Factory : CallAdapter.Factory() {

        @Suppress("ReturnCount")
        override fun get(
            returnType: Type,
            annotations: Array<out Annotation>,
            retrofit: Retrofit,
        ): CallAdapter<*, *>? {
            if (returnType !is ParameterizedType) return null

            val isFlow = getRawType(returnType) == Flow::class.java
            val isCall = getRawType(returnType) == Call::class.java
            if (!isFlow && !isCall) return null

            val netStatus = getParameterUpperBound(0, returnType)
            if (getRawType(netStatus) != NetRequestStatus::class.java) return null

            val bodyType = getParameterUpperBound(0, netStatus as ParameterizedType)

            return if (isFlow) {
                @Suppress("UNCHECKED_CAST")
                val defaultAdapter = retrofit.nextCallAdapter(
                    this,
                    object : ParameterizedType {
                        val typeArgs = arrayOf(bodyType)
                        override fun getActualTypeArguments() = typeArgs
                        override fun getRawType() = Call::class.java
                        override fun getOwnerType() = null
                    },
                    annotations,
                ) as CallAdapter<Any, Call<Any>>

                NetRequestStatusFlowAdapter(defaultAdapter)
            } else {
                @Suppress("UNCHECKED_CAST")
                val defaultAdapter = retrofit.nextCallAdapter(
                    this,
                    returnType,
                    annotations,
                ) as CallAdapter<NetRequestStatus<Any>, Call<NetRequestStatus<Any>>>

                NetRequestStatusAdapter(defaultAdapter)
            }
        }
    }
}

/**
 * Because [NetRequestStatus] itself is not an async abstraction, a type converter
 * for response bodies will allow us to have suspend functions directly return as
 * a [NetRequestStatus]. If this type converter is used without the [NetRequestStatusAdapter]
 * you must still try/catch any suspending function call in order to catch any Http or IO exceptions.
 *
 * The [NetRequestStatusTypeConverter.Factory] should be registered before other
 * serialization converters when constructing a retrofit instance.
 */
class NetRequestStatusTypeConverter<T : Any>(
    private val bodyAdapter: Converter<ResponseBody, T>,
) : Converter<ResponseBody, NetRequestStatus<T>> {

    override fun convert(value: ResponseBody): NetRequestStatus<T> {
        return runCatching {
            bodyAdapter.convert(value)
                ?: error("NetRequestStatus does not support a null on success")
        }
            .mapCatching { NetRequestStatus.success(it) }
            .getOrElse { NetRequestStatus.error(it) }
    }

    object Factory : Converter.Factory() {

        override fun responseBodyConverter(
            type: Type,
            annotations: Array<out Annotation>,
            retrofit: Retrofit,
        ): Converter<ResponseBody, *>? {
            if (getRawType(type) != NetRequestStatus::class.java) return null
            val bodyType = getParameterUpperBound(0, type as ParameterizedType)
            val bodyAdapter = retrofit.nextResponseBodyConverter<Any>(null, bodyType, annotations)
            check(bodyAdapter !is NetRequestStatusTypeConverter<*>) {
                "Nested NetRequestStatus responses are not supported."
            }
            return NetRequestStatusTypeConverter<Any>(bodyAdapter)
        }
    }
}
