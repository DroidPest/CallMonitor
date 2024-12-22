package com.mk.assessment

import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewModelScope
import com.mk.assessment.navigation.LocalAppState
import com.mk.assessment.navigation.LocalServerState
import com.mk.assessment.navigation.Navigation
import com.mk.assessment.navigation.Routes
import com.mk.assessment.navigation.rememberAssessmentAppState
import com.mk.infrastructure.AuthenticationState
import com.mk.networking.AssessmentServer
import com.mk.theme.MainTheme
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch
import javax.inject.Inject

@AndroidEntryPoint
class MainActivity : BaseActivity() {
    @Inject lateinit var assessmentServer: AssessmentServer

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel.viewModelScope.launch {
            viewModel.authenticateUser().await()
            setAppContent(getStartDestination())
        }
    }

    private fun setAppContent(startDestination: String) {
        setContent {
            CompositionLocalProvider(
                LocalAppState provides rememberAssessmentAppState(),
                LocalServerState provides assessmentServer,
            ) {
                val userState by viewModel.userState().collectAsStateWithLifecycle()
                appState = LocalAppState.current

                if (
                    userState.authenticationState == AuthenticationState.NOT_AUTHENTICATED &&
                    appState.navController.currentDestination?.route != null
                ) {
                    appState.navigateTo(Routes.LoginScreen)
                }

                MainTheme {
                    Scaffold { innerPadding ->
                        Surface {
                            Navigation(
                                modifier = Modifier.padding(innerPadding),
                                startDestination = startDestination,
                            )
                        }
                    }
                }
            }
        }
    }
}
