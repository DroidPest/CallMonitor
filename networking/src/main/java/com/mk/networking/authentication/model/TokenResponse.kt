package com.mk.networking.authentication.model

import com.google.gson.annotations.SerializedName

data class TokenResponse(
    @SerializedName("token_type")
    val tokenType: String,
    @SerializedName("expires_in")
    val expiresIn: String,
    @SerializedName("access_token")
    val accessToken: String,
)
