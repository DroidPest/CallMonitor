package com.mk.theme.buttons

import androidx.compose.foundation.BorderStroke
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp

data class ButtonBorderStroke(
    val default: BorderStroke? = null,
    val outline: BorderStroke? = BorderStroke(1.dp, Color(color = 0xFF2D2D2D)),
) {
    fun outline(color: Color): BorderStroke = BorderStroke(1.dp, color)
}
