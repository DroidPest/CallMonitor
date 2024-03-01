package com.mk.theme.appbar

import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.mk.theme.colors.ColorScheme

data class AppBarStyles(
    val colors: ColorScheme,
    val default: AppBarStyle = AppBarStyle(
        leftIconSize = 30.dp,
        rightIconSize = 30.dp,
        titleSize = 15.sp,
        titleAlignment = TextAlign.Center,
        titleFontWeight = FontWeight.W600,
    ),
)

data class AppBarStyle(
    val leftIconSize: Dp,
    val rightIconSize: Dp,
    val titleSize: TextUnit,
    val titleAlignment: TextAlign,
    val titleFontWeight: FontWeight,
)
