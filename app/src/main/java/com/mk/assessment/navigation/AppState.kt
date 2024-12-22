package com.mk.assessment.navigation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.compositionLocalOf
import androidx.compose.runtime.remember
import androidx.navigation.NavHostController
import androidx.navigation.NavOptionsBuilder
import androidx.navigation.compose.rememberNavController
import com.mk.networking.AssessmentServer

class AppState(val navController: NavHostController) {
    fun navigateTo(screen: Routes, builder: NavOptionsBuilder.() -> Unit = {}) {
        if (alignRoutes(screen)) navController.navigate(screen.name, builder = builder)
    }

    private fun alignRoutes(screen: Routes): Boolean {
        if (navController.currentDestination?.route != screen.name) {
            // screen is in backstack, return to it
            if (navController.popBackStack(screen.name, false)) {
                return false
            }
            // clear back stack so home is always the root
            if (screen.name == Routes.HomeScreen.name) {
                while (navController.popBackStack()) {
                    // pop off backstack till HomeScreen
                }
            }
            return true
        }
        return false
    }
}

@Composable
fun rememberAssessmentAppState(navController: NavHostController = rememberNavController()) =
    remember(navController) {
        AppState(navController = navController)
    }

@Suppress("TooGenericExceptionThrown")
val LocalAppState = compositionLocalOf<AppState> { throw Exception("Missing App State composition") }

@Suppress("TooGenericExceptionThrown")
val LocalServerState = compositionLocalOf<AssessmentServer> { throw Exception("Missing Server State composition") }
