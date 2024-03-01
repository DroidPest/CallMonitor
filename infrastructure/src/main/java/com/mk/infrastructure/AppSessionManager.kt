package com.mk.infrastructure

import com.mk.infrastructure.models.UserData
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import javax.inject.Inject

interface AppSessionManager {
    val userDataState: MutableStateFlow<UserData>
    val networkState: StateFlow<NetworkState>

    fun isUserAuthenticated(): Boolean
}

class AssessmentAppSessionManager @Inject constructor(
    networkStateManager: NetworkStateManager,
) : AppSessionManager {
    override val userDataState: MutableStateFlow<UserData> = MutableStateFlow(UserData())
    override val networkState: StateFlow<NetworkState> =
        networkStateManager.observeConnectivityAsFlow()

    override fun isUserAuthenticated(): Boolean =
        userDataState.value.authenticationState.value == AuthenticationState.AUTHENTICATED
}

enum class AuthenticationState {
    AUTHENTICATED,
    NOT_AUTHENTICATED,
}

enum class NetworkState {
    IDLE,
    AVAILABLE,
    UNAVAILABLE,
}
