package com.mk.assessment

import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.viewModelScope
import com.mk.assessment.navigation.LocalAppState
import com.mk.assessment.navigation.Navigation
import com.mk.assessment.navigation.rememberAssessmentAppState
import com.mk.theme.MainTheme
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class MainActivity : BaseActivity() {

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
            ) {
                appState = LocalAppState.current

                MainTheme {
                    Scaffold(
                        bottomBar = {
//                            NavigationBar(
//                                // This filter is used till we finish the feature, same with isFeatureEnabled
//                                Tabs.values()
//                                    .filter {
//                                        if (it.title == R.string.tab_shop) {
//                                            isFeatureEnabled.observe(FeatureModel.Code.SHOPIFY).collectAsState().value
//                                        } else {
//                                            true
//                                        }
//                                    }
//                                    .toTypedArray(),
//                                LocalAppState.current.navController
//                            )
                        },
                    ) { innerPadding ->
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

    @Preview(showBackground = true)
    @Composable
    fun GreetingPreview() {
        setAppContent(getStartDestination())
    }
}
