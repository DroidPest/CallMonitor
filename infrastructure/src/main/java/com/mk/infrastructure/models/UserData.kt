package com.mk.infrastructure.models

import com.mk.infrastructure.AuthenticationState

data class UserData(
    val authenticationState: AuthenticationState = AuthenticationState.NOT_AUTHENTICATED,
    val isGuest: Boolean = true,
)
