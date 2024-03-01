package com.mk.assessment

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.mk.infrastructure.AppSessionManager
import com.mk.infrastructure.AuthenticationState
import com.mk.infrastructure.enumValueOrNull
import com.mk.localstorage.sharedpref.SharedPreferencesManager
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Deferred
import kotlinx.coroutines.async
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import javax.inject.Inject

sealed interface MainActivityUiState {
    data object Loading : MainActivityUiState
    data object Content : MainActivityUiState
}

@HiltViewModel
class MainActivityViewModel @Inject constructor(
    private val sessionManager: AppSessionManager,
    private val sharedPreferences: SharedPreferencesManager,
) : ViewModel() {
    private val _uiState = MutableStateFlow<MainActivityUiState>(MainActivityUiState.Loading)
    val uiState: StateFlow<MainActivityUiState> = _uiState

    fun isUserAuthenticated() = sessionManager.isUserAuthenticated()
    fun isUserInit() = sessionManager.userDataState.value == null

    fun authenticateUser(): Deferred<Unit> {
        return viewModelScope.async {
            sessionManager.userDataState.value.authenticationState.emit(
                enumValueOrNull(
                    sharedPreferences.getString(
                        AuthenticationState::class.java.name,
                        AuthenticationState.NOT_AUTHENTICATED.name,
                    ),
                    AuthenticationState.NOT_AUTHENTICATED,
                ),
            )
            _uiState.emit(MainActivityUiState.Content)
        }
    }
}
