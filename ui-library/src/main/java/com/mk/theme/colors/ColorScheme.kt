package com.mk.theme.colors

import androidx.compose.ui.graphics.Color

@Suppress("MagicNumber")
data class ColorScheme(
    private val isDarkTheme: Boolean,
    private val colors: Colors,

    val transparent: Color = colors.transparent,

    val neutral1: Color = if (isDarkTheme) colors.darkNeutral1 else colors.lightNeutral1,
    val neutral2: Color = if (isDarkTheme) colors.darkNeutral2 else colors.lightNeutral2,
    val neutral3: Color = if (isDarkTheme) colors.darkNeutral3 else colors.lightNeutral3,
    val neutral4: Color = if (isDarkTheme) colors.darkNeutral4 else colors.lightNeutral4,
    val neutral5: Color = if (isDarkTheme) colors.darkNeutral5 else colors.lightNeutral5,
    val neutral6: Color = if (isDarkTheme) colors.darkNeutral6 else colors.lightNeutral6,
    val neutral7: Color = if (isDarkTheme) colors.darkNeutral7 else colors.lightNeutral7,
    val neutral8: Color = if (isDarkTheme) colors.darkNeutral8 else colors.lightNeutral8,

    val primary1: Color = if (isDarkTheme) colors.darkPrimary1 else colors.lightPrimary1,
    val primary2: Color = if (isDarkTheme) colors.darkPrimary2 else colors.lightPrimary2,
    val primary3: Color = if (isDarkTheme) colors.darkPrimary3 else colors.lightPrimary3,
    val primary4: Color = if (isDarkTheme) colors.darkPrimary4 else colors.lightPrimary4,

    val secondary1: Color = if (isDarkTheme) colors.darkSecondary1 else colors.lightSecondary1,
    val secondary2: Color = if (isDarkTheme) colors.darkSecondary2 else colors.lightSecondary2,
    val secondary3: Color = if (isDarkTheme) colors.darkSecondary3 else colors.lightSecondary3,

    val critical1: Color = if (isDarkTheme) colors.darkCritical1 else colors.lightCritical1,
    val critical2: Color = if (isDarkTheme) colors.darkCritical2 else colors.lightCritical2,

    val complementary1: Color = if (isDarkTheme) colors.darkComplementary1 else colors.lightComplementary1,
    val complementary2: Color = if (isDarkTheme) colors.darkComplementary2 else colors.lightComplementary2,
    val complementary3: Color = if (isDarkTheme) colors.darkComplementary3 else colors.lightComplementary3,
    val complementary4: Color = if (isDarkTheme) colors.darkComplementary4 else colors.lightComplementary4,

    val sooRed: Color = colors.sooRed, // The same for dark and light modes
    val sooGreen: Color = colors.sooGreen, // The same for dark and light modes
)
