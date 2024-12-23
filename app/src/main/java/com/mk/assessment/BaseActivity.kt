package com.mk.assessment

import android.Manifest
import android.content.pm.PackageManager
import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.viewModels
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.core.app.ActivityCompat
import androidx.core.splashscreen.SplashScreen
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import callLogs.ContactLoggerManager
import com.mk.assessment.navigation.AppState
import com.mk.assessment.navigation.Routes
import com.mk.infrastructure.lifecycle.AppVisibilityUtil
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import javax.inject.Inject

open class BaseActivity : ComponentActivity() {
    protected val viewModel: MainActivityViewModel by viewModels()
    protected lateinit var appState: AppState

    @Inject lateinit var contactLoggerManager: ContactLoggerManager

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val splashScreen = installSplashScreen()
        splashScreen(splashScreen)
        checkPermissions()
    }

    protected fun getStartDestination(): String {
        return if (viewModel.isUserAuthenticated()) {
            Routes.HomeScreen.name
        } else {
            Routes.HomeScreen.name
//            Routes.LoginScreen.name
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

    /**
     * Cheap, dirty check for permissions
     * This is unsafe, and has alot of holes using old runtime checks
     * --- NOT POLISHED ---
     */
    companion object {
        const val ALL_PERMISSIONS = 101
    }

    private fun checkPermissions() {
        val permissions = arrayOf(
            Manifest.permission.READ_CALL_LOG,
            Manifest.permission.READ_CONTACTS,
            Manifest.permission.READ_PHONE_STATE,
            Manifest.permission.PROCESS_OUTGOING_CALLS
        )
        ActivityCompat.requestPermissions(this, permissions, ALL_PERMISSIONS)
    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<String>,
        grantResults: IntArray,
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        if (requestCode == ALL_PERMISSIONS) {
            var isPermissionDenied = grantResults.firstOrNull { it != PackageManager.PERMISSION_GRANTED } != null

            if (isPermissionDenied) {
                Toast.makeText(this, "Permissions not granted but needed", Toast.LENGTH_LONG).show()
                finish()
            } else {
                // not the best place for this, should be self init, but would need to work permissions more
                contactLoggerManager.initCallLogManager()
            }
        }
    }
}
