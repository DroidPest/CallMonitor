package com.mk.networking.authentication

import com.mk.infrastructure.safeLet
import com.mk.networking.BuildConfig
import com.mk.networking.authentication.model.TokenBody
import com.mk.networking.authentication.service.TokenService
import timber.log.Timber
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class AuthenticationManager @Inject constructor(
    private val service: TokenService,
) {
    private var accessToken: String? = null
    private var timeout: Long = 0

    fun handleAuthenticationTokenBlocking(): String? {
        return synchronized(service) {
            if (accessToken == null || System.currentTimeMillis() > timeout) {
                val call = service.refreshToken(TokenBody())
                runCatching { call.execute() }
                    .fold(
                        onSuccess = { result ->
                            if (!result.isSuccessful) {
                                result.errorBody()?.let { errorBody ->
                                    if (BuildConfig.DEBUG) {
                                        Timber.d("Error encountered: ${errorBody.string()}")
                                    }
                                }
                                return null
                            }
                            result.body()?.let { response ->
                                safeLet(
                                    response.tokenType,
                                    response.accessToken,
                                    response.expiresIn,
                                ) { _, _accessToken, _expires ->
                                    accessToken = _accessToken
                                    timeout =
                                        System.currentTimeMillis() + (_expires.toLong() * 1000)
                                    accessToken
                                }
                            }
                        },
                        onFailure = { exception ->
                            Timber.e("Unable to refresh token", exception)
                            null
                        },
                    )
            } else {
                accessToken
            }
        }
    }
}
