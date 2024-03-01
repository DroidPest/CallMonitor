package com.mk.theme.buttons

import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp

data class ButtonElevations(
    val default: ButtonElevation = ButtonElevation(0.dp, 0.dp, 0.dp, 1.dp, 0.dp),
)

data class ButtonElevation(
    val defaultElevation: Dp,
    val pressedElevation: Dp,
    val focusedElevation: Dp,
    val hoveredElevation: Dp,
    val disabledElevation: Dp,
)
