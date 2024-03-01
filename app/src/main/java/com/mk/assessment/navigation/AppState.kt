package com.mk.assessment.navigation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.compositionLocalOf
import androidx.compose.runtime.remember
import androidx.navigation.NavHostController
import androidx.navigation.NavOptionsBuilder
import androidx.navigation.compose.rememberNavController

class AppState(val navController: NavHostController) {
    fun navigateTo(screen: Routes, builder: NavOptionsBuilder.() -> Unit = {}) {
        if (alignRoutes(screen)) navController.navigate(screen.name, builder = builder)
    }

    fun <T : Any?> navigateToWithParams(route: Routes, params: T? = null, builder: NavOptionsBuilder.() -> Unit = {}) {
        if (alignRoutes(route)) {
            val screen = params?.let { createParamScreen(route, params) } ?: route.name

            navController.navigate(screen, builder = builder)
        }
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

    private fun <T : Any> createParamScreen(route: Routes, params: T): String =
        when (route) {
//            Routes.PublicProfileScreen -> route.name + "/contentCreatorId=" + params.toString()
//            Routes.FollowersScreen -> route.name + "/userId=" + params.toString()
//            Routes.FollowingScreen -> route.name + "/userId=" + params.toString()
//            Routes.NowScreen -> route.name + "?postSlug=$params"
            else -> route.name
        }
}

@Composable
fun rememberAssessmentAppState(navController: NavHostController = rememberNavController()) =
    remember(navController) {
        AppState(navController = navController)
    }

@Suppress("TooGenericExceptionThrown")
val LocalAppState = compositionLocalOf<AppState> { throw Exception("Missing App State composition") }
