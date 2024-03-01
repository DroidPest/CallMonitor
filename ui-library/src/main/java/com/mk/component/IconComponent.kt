package com.mk.component

import androidx.annotation.DrawableRes
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.LocalContentColor
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.mk.theme.LocalTheme

interface IconComponent {

    @Composable
    private fun defaultIcon(
        painter: Painter,
        contentDescription: String,
        modifier: Modifier,
        tint: Color,
    ) {
        Icon(
            painter = painter,
            contentDescription = contentDescription,
            modifier = modifier,
            tint = tint,
        )
    }

    @Composable
    private fun defaultImage(
        painter: Painter,
        contentDescription: String,
        modifier: Modifier,
    ) {
        Image(
            painter = painter,
            contentDescription = contentDescription,
            modifier = modifier,
        )
    }

    @Composable
    fun primary(
        painter: Painter,
        modifier: Modifier = Modifier,
        iconSize: IconSize = IconSize.SMALL,
        contentDescription: String? = null,
        tint: Color = LocalContentColor.current,
    ) {
        defaultIcon(
            painter = painter,
            contentDescription = contentDescription ?: painter.toString(),
            modifier = modifier.then(Modifier.size(iconSize.size)),
            tint = tint,
        )
    }

    @Composable
    fun clickableIcon(@DrawableRes icon: Int, description: String, onClick: () -> Unit) {
        val theme = LocalTheme.current

        IconButton(
            onClick = onClick,
        ) {
            Icon(
                painter = painterResource(id = icon),
                contentDescription = description,
                modifier = Modifier
                    .height(theme.appBarStyles.default.leftIconSize),
                tint = MaterialTheme.colorScheme.onSurface,
            )
        }
    }

    @Composable
    fun clickableIcon(icon: ImageVector, description: String, onClick: () -> Unit) {
        val theme = LocalTheme.current

        IconButton(
            onClick = onClick,
        ) {
            Icon(
                imageVector = icon,
                contentDescription = description,
                modifier = Modifier
                    .height(theme.appBarStyles.default.leftIconSize),
                tint = MaterialTheme.colorScheme.onSurface,
            )
        }
    }

    data class SelectableIcons(
        @DrawableRes val default: Int,
        @DrawableRes val selected: Int,
    )

    @Composable
    fun selectableMenuIcon(
        icons: SelectableIcons,
        isSelected: Boolean,
        modifier: Modifier = Modifier,
        iconSize: IconSize = IconSize.SMALL,
        contentDescription: String? = null,
    ) {
        val painter = if (isSelected) icons.selected else icons.default
        defaultImage(
            painter = painterResource(id = painter),
            contentDescription = contentDescription ?: painter.toString(),
            modifier = modifier.then(Modifier.size(iconSize.size)),
        )
    }

    enum class IconSize(val size: Dp) {
        SMALL(16.dp), CUSTOM_TAB(22.dp), MEDIUM(32.dp), LARGE(64.dp), EXTRA_LARGE(128.dp)
    }
}

val UiLibrary.IconComponent
    get() = object : IconComponent {}
