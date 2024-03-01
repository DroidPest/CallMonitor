package com.mk.assessment

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.viewModels
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.core.splashscreen.SplashScreen
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import com.mk.assessment.navigation.AppState
import com.mk.assessment.navigation.Routes
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch

open class BaseActivity : ComponentActivity() {
    protected val viewModel: MainActivityViewModel by viewModels()
    protected lateinit var appState: AppState

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val splashScreen = installSplashScreen()
        splashScreen(splashScreen)
    }

    protected fun getStartDestination(): String {
        return if (viewModel.isUserAuthenticated()) {
            Routes.HomeScreen.name
        } else {
            Routes.LoginScreen.name
        }
    }

    private fun splashScreen(splashScreen: SplashScreen) {
        var uiState: MainActivityUiState by mutableStateOf(MainActivityUiState.Loading)
        lifecycleScope.launch {
            lifecycle.repeatOnLifecycle(Lifecycle.State.STARTED) {
                viewModel.uiState
                    .onEach {
                        uiState = it
                    }.collect {
                        splashScreen.setKeepOnScreenCondition {
                            val loadingState = when (uiState) {
                                MainActivityUiState.Loading -> true
                                MainActivityUiState.Content -> false
                            }
                            val credCheck = viewModel.isUserInit()

                            (loadingState || credCheck)
                        }
                    }
            }
        }
    }
}
