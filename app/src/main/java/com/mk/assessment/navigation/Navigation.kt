package com.mk.assessment.navigation

import androidx.compose.foundation.background
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.mk.assessment.navigation.Routes.HomeScreen
import com.mk.assessment.navigation.Routes.LoginScreen
import com.mk.assessment.screens.home.HomeScreen
import com.mk.assessment.screens.login.LoginScreen

@Composable
fun Navigation(
    modifier: Modifier,
    startDestination: String = HomeScreen.name,
) {
    val appState = LocalAppState.current

    NavHost(
        navController = appState.navController,
        startDestination = startDestination,
        modifier = modifier
            .background(MaterialTheme.colorScheme.background),
    ) {
        composable(HomeScreen.name) { HomeScreen(hiltViewModel()) }
        composable(LoginScreen.name) { LoginScreen(appState, hiltViewModel()) }
    }
}
