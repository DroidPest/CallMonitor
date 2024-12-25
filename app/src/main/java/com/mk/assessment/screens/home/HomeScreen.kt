package com.mk.assessment.screens.home

import android.widget.Toast
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.mk.assessment.R
import com.mk.assessment.navigation.LocalServerState
import com.mk.component.ButtonComponent
import com.mk.component.TextComponent
import com.mk.component.UiLibrary
import com.mk.networking.AssessmentServer
import com.mk.theme.LocalTheme

@Composable
fun HomeScreen(viewModel: HomeScreenViewModel) {
    val server = LocalServerState.current
    val context = LocalContext.current
    var isServerActive = remember { mutableStateOf(false) }
    var isServerLoading = remember { mutableStateOf(false) }
    val errorMessage = viewModel.errorMessages.collectAsStateWithLifecycle(null)

    LaunchedEffect(key1 = errorMessage.value) {
        errorMessage.value?.let {
            isServerActive.value = !isServerActive.value
            isServerLoading.value = false

            viewModel.showErrorMessage(null)
            Toast.makeText(context, it, Toast.LENGTH_SHORT).show()
        }
    }

    DisposableEffect(viewModel) {
        onDispose {
            server.stop {
                viewModel.showErrorMessage("Failed to stop server, restart app if issue persists")
            }
        }
    }

    Column(
        modifier = Modifier.padding(horizontal = 16.dp).padding(top = 16.dp),
    ) {
        Row {
            val localColors = LocalTheme.current.colorScheme

            UiLibrary.ButtonComponent.primary(
                enabled = !isServerLoading.value,
                isLoading = isServerLoading.value,
                text =
                if (isServerActive.value) {
                    R.string.home_screen_stop_server
                } else {
                    R.string.home_screen_start_server
                },
                onClick = {
                    serverControl(server, isServerActive, isServerLoading) {
                        viewModel.showErrorMessage(it)
                    }
                },
            )
            UiLibrary.TextComponent.h3(
                modifier = Modifier.align(Alignment.CenterVertically).padding(8.dp),
                text = server.host,
                color = if (isServerActive.value) localColors.sooGreen else localColors.sooRed,
            )
//            UiLibrary.ButtonComponent.primary(
//                enabled = !isServerLoading.value,
//                isLoading = isServerLoading.value,
//                text = R.string.home_screen_reset_server,
//                onClick = {
//                    server.resetServer()
//                },
//            )
        }

        ShowCallLogs(modifier = Modifier.weight(1f), viewModel = viewModel)
    }
}

private fun serverControl(
    server: AssessmentServer,
    isServerActive: MutableState<Boolean>,
    isServerLoading: MutableState<Boolean>,
    showMessage: (message: String) -> Unit,
) {
    isServerLoading.value = true
    if (isServerActive.value) {
        server.stop {
            showMessage("Failed to stop server, restart app if issue persists")
        }
    } else {
        server.start {
            showMessage("Failed to start server, check networking, restart app if issue persists")
        }
    }
    // assuming that the server started/stopped with no errors
    isServerActive.value = !isServerActive.value
    isServerLoading.value = false
}

@Composable
fun ShowCallLogs(modifier: Modifier, viewModel: HomeScreenViewModel) {
    val callLogs by viewModel.getCallLogFlow().collectAsStateWithLifecycle()

    LazyColumn(
        modifier = modifier,
        contentPadding = PaddingValues(vertical = 12.dp),
        verticalArrangement = Arrangement.spacedBy(6.dp),
    ) {
        items(items = callLogs) { log ->
            UiLibrary.TextComponent.h3(text = "${log.callerName} :: ${log.duration}")
        }
    }
}
