package com.mk.assessment.screens.login

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.mk.assessment.R
import com.mk.assessment.navigation.AppState
import com.mk.assessment.navigation.Routes
import com.mk.component.ButtonComponent
import com.mk.component.LoadingComponent
import com.mk.component.TextComponent
import com.mk.component.UiLibrary
import com.mk.infrastructure.AuthenticationState

@Composable
fun LoginScreen(appState: AppState, viewModel: LoginScreenViewModel) {
    val isLoading by viewModel.isLoading.collectAsStateWithLifecycle()

    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center,
    ) {
        val userAuthenticationState by viewModel.getUserAuthenticationSessionState().collectAsStateWithLifecycle()

        LaunchedEffect(key1 = userAuthenticationState) {
            if (userAuthenticationState.authenticationState == AuthenticationState.AUTHENTICATED) {
                appState.navigateTo(Routes.HomeScreen)
            }
        }

        UiLibrary.TextComponent.h1(text = R.string.login_screen_login_title)
        UiLibrary.ButtonComponent.primary(
            enabled = !isLoading,
            text = R.string.user_login,
            onClick = {
                // This sets user authentication status to AUTHENTICATED, it is done in memory
                // so there is no need to wait till operation is complete to go to HomeScreen
                viewModel.setUserAuthenticated(AuthenticationState.AUTHENTICATED)
            },
        )
    }
    if (isLoading) UiLibrary.LoadingComponent.LoadingCentered()
}
