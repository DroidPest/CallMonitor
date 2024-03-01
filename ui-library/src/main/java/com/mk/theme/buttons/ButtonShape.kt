package com.mk.theme.buttons

import androidx.compose.foundation.shape.CircleShape
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.graphics.Shape

data class ButtonShape(
    val default: Shape = CircleShape,
    val rectangle: Shape = RectangleShape,
)
