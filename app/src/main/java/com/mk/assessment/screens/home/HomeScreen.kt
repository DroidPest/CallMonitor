package com.mk.assessment.screens.home

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
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

    DisposableEffect(viewModel) {
        onDispose {
            server.stop()
        }
    }

    Column(
        modifier = Modifier.padding(horizontal = 16.dp).padding(top = 16.dp),
    ) {
        Row {
            val localColors = LocalTheme.current.colorScheme
            var isServerActive = remember { mutableStateOf(false) }
            var isServerLoading = remember { mutableStateOf(false) }

            UiLibrary.ButtonComponent.primary(
                enabled = !isServerLoading.value,
                isLoading = isServerLoading.value,
                text =
                if (isServerActive.value) {
                    R.string.home_screen_stop_server
                } else {
                    R.string.home_screen_start_server
                },
                onClick = { serverControl(server, isServerActive, isServerLoading) },
            )
            UiLibrary.TextComponent.h3(
                modifier = Modifier.align(Alignment.CenterVertically).padding(8.dp),
                text = server.host,
                color = if (isServerActive.value) localColors.sooGreen else localColors.sooRed,
            )
        }

        ShowCallLogs(modifier = Modifier.weight(1f), viewModel = viewModel)
    }
}

private fun serverControl(
    server: AssessmentServer,
    isServerActive: MutableState<Boolean>,
    isServerLoading: MutableState<Boolean>,
) {
    isServerLoading.value = true
    if (isServerActive.value) {
        server.stop()
    } else {
        server.start()
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
            UiLibrary.TextComponent.h3(text = log)
        }
    }
}
