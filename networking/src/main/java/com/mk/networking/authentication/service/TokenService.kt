package com.mk.networking.authentication.service

import com.mk.networking.authentication.model.TokenBody
import com.mk.networking.authentication.model.TokenResponse
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.POST

interface TokenService {
    @POST("/v2/oauth2/token")
    fun refreshToken(@Body refreshTokenRequest: TokenBody): Call<TokenResponse>
}
