package com.mk.component

import androidx.annotation.DrawableRes
import androidx.compose.foundation.layout.height
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import com.mk.theme.LocalTheme

interface IconComponent {
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
}

val UiLibrary.IconComponent
    get() = object : IconComponent {}
