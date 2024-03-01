package com.mk.assessment.screens.login

import androidx.compose.runtime.Composable
import com.mk.assessment.R
import com.mk.assessment.navigation.AppState
import com.mk.assessment.navigation.Routes
import com.mk.component.ButtonComponent
import com.mk.component.TextComponent
import com.mk.component.UiLibrary
import com.mk.infrastructure.AuthenticationState

@Composable
fun LoginScreen(appState: AppState, viewModel: LoginScreenViewModel) {
    UiLibrary.TextComponent.h1(text = R.string.login_screen_login_title)
    UiLibrary.ButtonComponent.primary(
        text = R.string.login_screen_login_button_text,
        onClick = {
            // This sets user authentication status to AUTHENTICATED, it is done in memory
            // so there is no need to wait till operation is complete to go to HomeScreen
            viewModel.setUserAuthenticated(AuthenticationState.AUTHENTICATED)
            appState.navigateTo(Routes.HomeScreen)
        },
    )
}
