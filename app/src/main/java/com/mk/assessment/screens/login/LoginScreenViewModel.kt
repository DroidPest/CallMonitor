package com.mk.assessment.screens.login

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.mk.infrastructure.AppSessionManager
import com.mk.infrastructure.AuthenticationState
import com.mk.infrastructure.di.IoDispatcher
import com.mk.localstorage.sharedpref.SharedPreferencesManager
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

@HiltViewModel
class LoginScreenViewModel @Inject constructor(
    private val sessionManager: AppSessionManager,
    private val sharedPreferences: SharedPreferencesManager,
    @IoDispatcher private val ioDispatcher: CoroutineDispatcher,
) : ViewModel() {
    private val _isLoading: MutableStateFlow<Boolean> = MutableStateFlow(false)
    val isLoading: StateFlow<Boolean> = _isLoading.asStateFlow()

    fun getUserAuthenticationSessionState() = sessionManager.userDataState

    fun setUserAuthenticated(authenticationStatus: AuthenticationState) = viewModelScope.launch {
        _isLoading.emit(true)
        withContext(ioDispatcher) {
            // no user authentication
            sessionManager.setUserAuthentication(authenticationState = authenticationStatus)

            sharedPreferences.putString(
                AuthenticationState::class.java.name,
                authenticationStatus.name,
            )
            _isLoading.emit(false)
        }
    }
}
