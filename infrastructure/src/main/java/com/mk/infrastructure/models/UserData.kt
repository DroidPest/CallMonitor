package com.mk.infrastructure.models

import com.mk.infrastructure.AuthenticationState
import kotlinx.coroutines.flow.MutableStateFlow

data class UserData(
    val authenticationState: MutableStateFlow<AuthenticationState> =
        MutableStateFlow(AuthenticationState.NOT_AUTHENTICATED),
    val isGuest: Boolean = true,
)
