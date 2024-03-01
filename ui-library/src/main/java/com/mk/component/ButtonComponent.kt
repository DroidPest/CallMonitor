package com.mk.component

import androidx.annotation.StringRes
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonColors
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ButtonElevation
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.mk.theme.LocalTheme

@Suppress("LongParameterList")
interface ButtonComponent {

    @Composable
    private fun defaultButton(
        onClick: () -> Unit,
        modifier: Modifier,
        enabled: Boolean,
        shape: Shape,
        colors: ButtonColors,
        elevation: ButtonElevation?,
        border: BorderStroke?,
        contentPadding: PaddingValues,
        interactionSource: MutableInteractionSource,
        content: @Composable RowScope.() -> Unit,
    ) {
        Button(
            onClick,
            modifier,
            enabled,
            shape,
            colors,
            elevation,
            border,
            contentPadding,
            interactionSource,
            content,
        )
    }

    @Composable
    fun primary(
        @StringRes text: Int,
        onClick: () -> Unit,
        modifier: Modifier = Modifier,
        icon: ImageVector? = null,
        iconTint: Color = Color.Unspecified,
        enabled: Boolean = true,
        interactionSource: MutableInteractionSource = remember { MutableInteractionSource() },
        colors: ButtonColors? = null,
        isLoading: Boolean = false,
        loadingColor: Color = LocalTheme.current.colorScheme.neutral1,
        contentPadding: PaddingValues? = null,
    ) {
        val appColors = LocalTheme.current.colorScheme
        with(LocalTheme.current.buttonStyle.primary) {
            val defaultColors = colors
                ?: ButtonDefaults.buttonColors(
                    containerColor = appColors.primary4,
                    contentColor = appColors.neutral1,
                    disabledContainerColor = appColors.primary2,
                    disabledContentColor = appColors.neutral1,
                )
            defaultButton(
                onClick = onClick,
                modifier = modifier,
                enabled = enabled,
                shape = shape,
                colors = defaultColors,
                elevation = ButtonDefaults.buttonElevation(
                    defaultElevation = elevation.defaultElevation,
                    pressedElevation = elevation.pressedElevation,
                    focusedElevation = elevation.focusedElevation,
                    hoveredElevation = elevation.hoveredElevation,
                    disabledElevation = elevation.disabledElevation,
                ),
                border = border,
                contentPadding = contentPadding ?: paddingValues,
                interactionSource = interactionSource,
                content = {
                    if (!isLoading) {
                        icon?.let {
                            Icon(
                                imageVector = icon,
                                contentDescription = stringResource(id = text),
                                tint = iconTint,
                            )
                            Spacer(modifier = Modifier.width(6.dp))
                        }
                        UiLibrary.TextComponent.primaryButtonText(text = stringResource(id = text))
                    } else {
                        CircularProgressIndicator(
                            modifier = Modifier.size(20.dp),
                            color = loadingColor,
                            strokeWidth = 2.dp,
                        )
                    }
                },
            )
        }
    }

    @Composable
    fun secondary(
        @StringRes text: Int,
        onClick: () -> Unit,
        modifier: Modifier = Modifier,
        enabled: Boolean = true,
        interactionSource: MutableInteractionSource = remember { MutableInteractionSource() },
    ) {
        with(LocalTheme.current.buttonStyle.secondary) {
            defaultButton(
                onClick = onClick,
                modifier = modifier,
                enabled = enabled,
                shape = shape,
                colors = ButtonDefaults.buttonColors(
                    containerColor = color.containerColor,
                    contentColor = color.contentColor,
                    disabledContainerColor = color.disabledContainerColor,
                    disabledContentColor = color.disabledContentColor,
                ),
                elevation = ButtonDefaults.buttonElevation(
                    defaultElevation = elevation.defaultElevation,
                    pressedElevation = elevation.pressedElevation,
                    focusedElevation = elevation.focusedElevation,
                    hoveredElevation = elevation.hoveredElevation,
                    disabledElevation = elevation.disabledElevation,
                ),
                border = border,
                contentPadding = paddingValues,
                interactionSource = interactionSource,
                content = {
                    UiLibrary.TextComponent.primaryButtonText(text = stringResource(id = text))
                },
            )
        }
    }

    @Composable
    fun primaryOutline(
        modifier: Modifier = Modifier,
        icon: Int? = null,
        text: String?,
        onClick: () -> Unit,
        enabled: Boolean = true,
        interactionSource: MutableInteractionSource = remember { MutableInteractionSource() },
    ) {
        with(LocalTheme.current.buttonStyle.outlinePrimary) {
            OutlinedButton(
                modifier = modifier,
                onClick = onClick,
                enabled = enabled,
                colors = ButtonDefaults.buttonColors(
                    containerColor = color.containerColor,
                    contentColor = color.contentColor,
                    disabledContainerColor = color.disabledContainerColor,
                    disabledContentColor = color.disabledContentColor,
                ),
                elevation = ButtonDefaults.buttonElevation(
                    defaultElevation = elevation.defaultElevation,
                    pressedElevation = elevation.pressedElevation,
                    focusedElevation = elevation.focusedElevation,
                    hoveredElevation = elevation.hoveredElevation,
                    disabledElevation = elevation.disabledElevation,
                ),
                border = border,
                contentPadding = paddingValues,
                interactionSource = interactionSource,
            ) {
                Row(verticalAlignment = Alignment.CenterVertically) {
                    icon?.let {
                        Icon(
                            imageVector = ImageVector.vectorResource(icon),
                            contentDescription = text,
                            tint = color.contentColor,
                        )
                        Spacer(modifier = Modifier.width(6.dp))
                    }
                    text?.let {
                        Text(
                            text = text,
                            textAlign = TextAlign.Center,
                            color = color.contentColor,
                        )
                    }
                }
            }
        }
    }
}

val UiLibrary.ButtonComponent
    get() = object : ButtonComponent {}
