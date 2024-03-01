package com.mk.component

import androidx.compose.material3.HorizontalDivider
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.mk.theme.LocalTheme

interface ListDividerComponent {
    @Composable
    fun default(
        modifier: Modifier = Modifier,
        thickness: Dp = 1.dp,
    ) {
        HorizontalDivider(
            modifier = modifier,
            thickness = thickness,
            color = LocalTheme.current.colorScheme.neutral5,
        )
    }
}

val UiLibrary.ListDividerComponent
    get() = object : ListDividerComponent {}
