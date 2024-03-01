package com.mk.theme.buttons

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shape
import com.mk.theme.colors.ColorScheme

data class ButtonStyles(
    val colors: ColorScheme,
    val shape: ButtonShape = ButtonShape(),
    val elevation: ButtonElevations = ButtonElevations(),
    val border: ButtonBorderStroke = ButtonBorderStroke(),
    val contentPadding: ButtonPaddingValues = ButtonPaddingValues(),

    val primary: AssessmentButtonStyle = AssessmentButtonStyle(
        AssessmentButtonColor(
            containerColor = colors.primary4,
            contentColor = colors.neutral1,
            disabledContainerColor = colors.primary3,
            disabledContentColor = colors.neutral8,
        ),
        shape.default,
        elevation.default,
        border.default,
        contentPadding.default,
    ),
    val secondary: AssessmentButtonStyle = AssessmentButtonStyle(
        AssessmentButtonColor(
            containerColor = colors.primary4,
            contentColor = colors.neutral1,
            disabledContainerColor = colors.primary3,
            disabledContentColor = colors.neutral8,
        ),
        shape.rectangle,
        elevation.default,
        border.default,
        contentPadding.default,
    ),

    val outlinePrimary: AssessmentButtonStyle = AssessmentButtonStyle(
        AssessmentButtonColor(
            containerColor = colors.transparent,
            contentColor = colors.neutral8,
            disabledContainerColor = colors.primary3,
            disabledContentColor = colors.neutral8,
        ),
        shape.default,
        elevation.default,
        border.outline,
        contentPadding.default,
    ),
)

data class AssessmentButtonStyle(
    val color: AssessmentButtonColor,
    val shape: Shape,
    val elevation: ButtonElevation,
    val border: BorderStroke?,
    val paddingValues: PaddingValues,
)

data class AssessmentButtonColor(
    val containerColor: Color,
    val contentColor: Color,
    val disabledContainerColor: Color,
    val disabledContentColor: Color,
)
