package com.mk.assessment.screens.login

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.mk.infrastructure.AppSessionManager
import com.mk.infrastructure.AuthenticationState
import com.mk.infrastructure.di.IoDispatcher
import com.mk.localstorage.sharedpref.SharedPreferencesManager
import com.mk.networking.authentication.AuthenticationManager
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

@HiltViewModel
class LoginScreenViewModel @Inject constructor(
    private val sessionManager: AppSessionManager,
    private val sharedPreferences: SharedPreferencesManager,
    private val authenticationManager: AuthenticationManager,
    @IoDispatcher private val ioDispatcher: CoroutineDispatcher,
) : ViewModel() {

    fun setUserAuthenticated(authenticationStatus: AuthenticationState) = viewModelScope.launch {
        withContext(ioDispatcher) {
            val accessToken = authenticationManager.handleAuthenticationTokenBlocking()

            if (accessToken != null) {
                sessionManager.userDataState.value.authenticationState.value = authenticationStatus
                sharedPreferences.putString(
                    AuthenticationState::class.java.name,
                    authenticationStatus.name,
                )
            }
        }
    }
}
