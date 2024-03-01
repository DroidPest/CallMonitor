package com.mk.networking.interceptor

import com.mk.networking.authentication.AuthenticationManager
import okhttp3.Interceptor
import okhttp3.Response
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class AuthenticationInterceptor @Inject constructor(
    private val authManager: AuthenticationManager,
) : Interceptor {

    override fun intercept(chain: Interceptor.Chain): Response {
        val authToken = authManager.handleAuthenticationTokenBlocking()

        val original = chain.request()
        return chain.proceed(
            original.newBuilder()
                .header("Authorization", "Bearer $authToken")
                .method(original.method, original.body)
                .build(),
        )
    }
}
