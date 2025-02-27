package com.mk.component

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview

interface LoadingComponent {
    @Composable
    fun LoadingCentered() {
        Box(
            modifier = Modifier.fillMaxSize(),
            contentAlignment = Alignment.Center,
        ) {
            CircularProgressIndicator()
        }
    }

    @Preview
    @Composable
    private fun LoadingScreenPreview() {
        LoadingCentered()
    }
}

val UiLibrary.LoadingComponent
    get() = object : LoadingComponent {}
