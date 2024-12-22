package com.mk.component

import android.annotation.SuppressLint
import androidx.annotation.DrawableRes
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.captionBar
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Surface
import androidx.compose.material3.TopAppBarColors
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.PreviewLightDark
import androidx.compose.ui.unit.dp
import com.mk.theme.MainTheme
import com.mk.ui_library.R

interface AppBarComponent {
    @Suppress("ABSTRACT_COMPOSABLE_DEFAULT_PARAMETER_VALUE")
    @OptIn(ExperimentalMaterial3Api::class)
    @Composable
    fun CenteredTopBar(
        modifier: Modifier = Modifier,
        colors: TopAppBarColors = TopAppBarDefaults.centerAlignedTopAppBarColors(),
        title: @Composable () -> Unit,
        subtitle: @Composable () -> Unit = {},
        @DrawableRes backIcon: Int? = null,
        onBackClick: () -> Unit,
        @SuppressLint(
            "ComposableLambdaParameterNaming",
            "ComposableLambdaParameterPosition",
        ) actionContent: (@Composable RowScope.() -> Unit) = {},
    ) {
        CenterAlignedTopAppBar(
            modifier = modifier.padding(horizontal = 5.dp),
            windowInsets = WindowInsets.captionBar,
            title = {
                Column(
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.spacedBy(space = 4.dp, alignment = Alignment.CenterVertically),
                ) {
                    title()
                    subtitle()
                }
            },
            colors = colors,
            navigationIcon = {
                backIcon?.let {
                    UiLibrary.IconComponent.clickableIcon(
                        icon = backIcon,
                        description = stringResource(id = R.string.app_bar_back_navigation_description),
                        onClick = onBackClick,
                    )
                }
            },
            actions = actionContent,
        )
    }
}

val UiLibrary.AppBarComponent
    get() = object : AppBarComponent {}

@OptIn(ExperimentalMaterial3Api::class)
@PreviewLightDark
@Composable
private fun AppBarPreviewScreen() {
    MainTheme {
        Surface {
            Column {
                UiLibrary.AppBarComponent.CenteredTopBar(
                    title = {
                        UiLibrary.TextComponent.h1(text = "Title message")
                    },
                    subtitle = {
                        UiLibrary.TextComponent.body1(text = "Subtitle message")
                    },
                    onBackClick = {},
                ) {
                    UiLibrary.ButtonComponent.primaryOutline(
                        text = "Click",
                        onClick = {},
                        modifier = Modifier.padding(horizontal = 10.dp),
                    )
                }
            }
        }
    }
}
