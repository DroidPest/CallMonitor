package com.mk.theme

import android.app.Activity
import android.view.Window
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.ProvidableCompositionLocal
import androidx.compose.runtime.staticCompositionLocalOf
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.platform.LocalContext
import androidx.core.view.WindowCompat
import com.mk.theme.MainTheme.colors
import com.mk.theme.appbar.AppBarStyles
import com.mk.theme.buttons.ButtonStyles
import com.mk.theme.colors.ColorScheme
import com.mk.theme.colors.Colors
import com.mk.theme.dimens.Elevations
import com.mk.theme.dimens.Paddings
import com.mk.theme.dimens.Spacings
import com.mk.theme.fonts.FontStyle

val LocalTheme: ProvidableCompositionLocal<MainTheme> =
    staticCompositionLocalOf { MainTheme }

val LOCAL_SPACINGS: ProvidableCompositionLocal<Spacings> =
    staticCompositionLocalOf { Spacings() }

val LOCAL_PADDINGS: ProvidableCompositionLocal<Paddings> =
    staticCompositionLocalOf { Paddings() }

val LOCAL_ELEVATIONS: ProvidableCompositionLocal<Elevations> =
    staticCompositionLocalOf { Elevations() }

val LOCAL_COLORS: ProvidableCompositionLocal<Colors> =
    staticCompositionLocalOf { Colors() }

object MainTheme {
    val spacings: Spacings @Composable get() = LOCAL_SPACINGS.current
    val padding: Paddings @Composable get() = LOCAL_PADDINGS.current
    val elevations: Elevations @Composable get() = LOCAL_ELEVATIONS.current
    val colorScheme: ColorScheme
        @Composable get() = ColorScheme(
            isSystemInDarkTheme(),
            LOCAL_COLORS.current,
        )
    val colors: Colors @Composable get() = LOCAL_COLORS.current
    val fontStyle: FontStyle @Composable get() = FontStyle(colorScheme)
    val buttonStyle: ButtonStyles @Composable get() = ButtonStyles(colorScheme)
    val appBarStyles: AppBarStyles @Composable get() = AppBarStyles(colorScheme)
}

@Composable
fun MainTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    content: @Composable () -> Unit,
) {
    val context = LocalContext.current
    val window: Window = (context as Activity).window
    val windowInsetsController = WindowCompat.getInsetsController(window, window.decorView)

    val colors = if (!darkTheme) {
        window.statusBarColor = Color.White.toArgb()
        windowInsetsController.isAppearanceLightStatusBars = true
        createLightColors()
    } else {
        window.statusBarColor = Color.Transparent.toArgb()
        windowInsetsController.isAppearanceLightStatusBars = false
        windowInsetsController.isAppearanceLightNavigationBars = false
        createDarkColors()
    }

    MaterialTheme(
        colorScheme = colors,
        content = content,
    )
}

@Composable
fun createLightColors() =
    lightColorScheme(
        primary = colors.mdThemeLightPrimary,
        onPrimary = colors.mdThemeLightOnPrimary,
        primaryContainer = colors.mdThemeLightPrimaryContainer,
        onPrimaryContainer = colors.mdThemeLightOnPrimaryContainer,
        secondary = colors.mdThemeLightSecondary,
        onSecondary = colors.mdThemeLightOnSecondary,
        secondaryContainer = colors.mdThemeLightSecondaryContainer,
        onSecondaryContainer = colors.mdThemeLightOnSecondaryContainer,
        tertiary = colors.mdThemeLightTertiary,
        onTertiary = colors.mdThemeLightOnTertiary,
        tertiaryContainer = colors.mdThemeLightTertiaryContainer,
        onTertiaryContainer = colors.mdThemeLightOnTertiaryContainer,
        error = colors.mdThemeLightError,
        errorContainer = colors.mdThemeLightErrorContainer,
        onError = colors.mdThemeLightOnError,
        onErrorContainer = colors.mdThemeLightOnErrorContainer,
        background = colors.mdThemeLightBackground,
        onBackground = colors.mdThemeLightOnBackground,
        surface = colors.mdThemeLightSurface,
        onSurface = colors.mdThemeLightOnSurface,
        surfaceVariant = colors.mdThemeLightSurfaceVariant,
        onSurfaceVariant = colors.mdThemeLightOnSurfaceVariant,
        outline = colors.mdThemeLightOutline,
        inverseOnSurface = colors.mdThemeLightInverseOnSurface,
        inverseSurface = colors.mdThemeLightInverseSurface,
        inversePrimary = colors.mdThemeLightInversePrimary,
        surfaceTint = colors.mdThemeLightSurfaceTint,
        outlineVariant = colors.mdThemeLightOutlineVariant,
        scrim = colors.mdThemeLightScrim,
    )

@Composable
fun createDarkColors() =
    darkColorScheme(
        primary = colors.mdThemeDarkPrimary,
        onPrimary = colors.mdThemeDarkOnPrimary,
        primaryContainer = colors.mdThemeDarkPrimaryContainer,
        onPrimaryContainer = colors.mdThemeDarkOnPrimaryContainer,
        secondary = colors.mdThemeDarkSecondary,
        onSecondary = colors.mdThemeDarkOnSecondary,
        secondaryContainer = colors.mdThemeDarkSecondaryContainer,
        onSecondaryContainer = colors.mdThemeDarkOnSecondaryContainer,
        tertiary = colors.mdThemeDarkTertiary,
        onTertiary = colors.mdThemeDarkOnTertiary,
        tertiaryContainer = colors.mdThemeDarkTertiaryContainer,
        onTertiaryContainer = colors.mdThemeDarkOnTertiaryContainer,
        error = colors.mdThemeDarkError,
        errorContainer = colors.mdThemeDarkErrorContainer,
        onError = colors.mdThemeDarkOnError,
        onErrorContainer = colors.mdThemeDarkOnErrorContainer,
        background = colors.mdThemeDarkBackground,
        onBackground = colors.mdThemeDarkOnBackground,
        surface = colors.mdThemeDarkSurface,
        onSurface = colors.mdThemeDarkOnSurface,
        surfaceVariant = colors.mdThemeDarkSurfaceVariant,
        onSurfaceVariant = colors.mdThemeDarkOnSurfaceVariant,
        outline = colors.mdThemeDarkOutline,
        inverseOnSurface = colors.mdThemeDarkInverseOnSurface,
        inverseSurface = colors.mdThemeDarkInverseSurface,
        inversePrimary = colors.mdThemeDarkInversePrimary,
        surfaceTint = colors.mdThemeDarkSurfaceTint,
        outlineVariant = colors.mdThemeDarkOutlineVariant,
        scrim = colors.mdThemeDarkScrim,
    )
