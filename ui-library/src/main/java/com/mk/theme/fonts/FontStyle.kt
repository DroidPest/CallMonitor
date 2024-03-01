package com.mk.theme.fonts

import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import com.broadcast.component.theme.fonts.LineHeight
import com.mk.theme.colors.ColorScheme

data class FontStyle(
    val colors: ColorScheme,
    val fontFamily: FontFamily = FontFamily(),
    val fontSize: FontSize = FontSize(),
    val lineHeight: LineHeight = LineHeight(),
    val fontSpacing: FontSpacing = FontSpacing(),

    // we can override the Theme colors but we are unsure on the approach so this is just to
    // remind us that we can control add color manually by using colors.textColor.
    // Ideally we want only one approach

    // Color needs to be Color. Unspecified for buttons so that its parent can change the color
    val button1: TextStyle = TextStyle(
        color = Color.Unspecified,
        fontSize = fontSize.font160,
        fontWeight = FontWeight.Normal,
        lineHeight = lineHeight.lineHeight200,
        fontStyle = FontStyle.Normal,
        fontFamily = fontFamily.default,
    ),

    val h1: TextStyle = TextStyle(
        color = Color.Unspecified,
        fontSize = fontSize.font220,
        fontWeight = FontWeight.Bold,
        lineHeight = lineHeight.lineHeight200,
        fontStyle = FontStyle.Normal,
        fontFamily = fontFamily.default,
        letterSpacing = fontSpacing.fontSpacing20,
    ),

    val h2: TextStyle = TextStyle(
        color = Color.Unspecified,
        fontSize = fontSize.font160,
        fontWeight = FontWeight.Bold,
        lineHeight = lineHeight.lineHeight200,
        fontStyle = FontStyle.Normal,
        fontFamily = fontFamily.default,
        letterSpacing = fontSpacing.fontSpacing20,
    ),

    val subHeader: TextStyle = TextStyle(
        color = Color.Unspecified,
        fontSize = fontSize.font140,
        fontWeight = FontWeight.Normal,
        lineHeight = lineHeight.lineHeight170,
        fontStyle = FontStyle.Normal,
        fontFamily = fontFamily.default,
        letterSpacing = fontSpacing.fontSpacing20,
    ),

    val h3: TextStyle = TextStyle(
        color = Color.Unspecified,
        fontSize = fontSize.font160,
        fontWeight = FontWeight.SemiBold,
        fontStyle = FontStyle.Normal,
        fontFamily = fontFamily.default,
    ),

    val body1: TextStyle = TextStyle(
        color = Color.Unspecified,
        fontSize = fontSize.font140,
        fontWeight = FontWeight.Medium,
        fontStyle = FontStyle.Normal,
        fontFamily = fontFamily.default,
    ),

    val body2: TextStyle = TextStyle(
        color = Color.Unspecified,
        fontSize = fontSize.font140,
        fontWeight = FontWeight.SemiBold,
        fontStyle = FontStyle.Normal,
        fontFamily = fontFamily.default,
    ),

    val body3: TextStyle = TextStyle(
        color = Color.Unspecified,
        fontSize = fontSize.font140,
        fontWeight = FontWeight.Bold,
        fontStyle = FontStyle.Normal,
        fontFamily = fontFamily.default,
    ),

    val caption1: TextStyle = TextStyle(
        color = Color.Unspecified,
        fontSize = fontSize.font120,
        fontWeight = FontWeight.Medium,
        fontStyle = FontStyle.Normal,
        fontFamily = fontFamily.default,
    ),

    val micro1: TextStyle = TextStyle(
        color = Color.Unspecified,
        fontSize = fontSize.font100,
        fontWeight = FontWeight.SemiBold,
        fontStyle = FontStyle.Normal,
        fontFamily = fontFamily.default,
    ),

    val textInput1: TextStyle = TextStyle(
        fontSize = fontSize.font150,
        fontWeight = FontWeight.W400,
        fontStyle = FontStyle.Normal,
        fontFamily = fontFamily.default,
    ),

    val textInputCounter: TextStyle = TextStyle(
        fontSize = fontSize.font120,
        fontWeight = FontWeight.W500,
        fontStyle = FontStyle.Normal,
        fontFamily = fontFamily.default,
    ),
)
