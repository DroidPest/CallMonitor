package com.mk.infrastructure

import com.mk.infrastructure.models.UserData
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import javax.inject.Inject

interface AppSessionManager {
    val userDataState: StateFlow<UserData>
    val networkState: StateFlow<NetworkState>

    fun setUserAuthentication(authenticationState: AuthenticationState)
    fun isUserAuthenticated(): Boolean
}

class AssessmentAppSessionManager @Inject constructor(
    networkStateManager: NetworkStateManager,
) : AppSessionManager {
    private val _userDataState: MutableStateFlow<UserData> = MutableStateFlow(UserData())
    override val userDataState: StateFlow<UserData> = _userDataState.asStateFlow()
    override val networkState: StateFlow<NetworkState> =
        networkStateManager.observeConnectivityAsFlow()

    override fun setUserAuthentication(authenticationState: AuthenticationState) {
        _userDataState.update {
            UserData(
                authenticationState = authenticationState,
            )
        }
    }

    override fun isUserAuthenticated(): Boolean =
        _userDataState.value.authenticationState == AuthenticationState.AUTHENTICATED
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
