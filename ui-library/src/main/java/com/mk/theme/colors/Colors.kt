package com.mk.theme.colors

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ElevatedButton
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FilledTonalButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.PreviewLightDark
import androidx.compose.ui.unit.dp
import com.mk.component.AppBarComponent
import com.mk.component.ButtonComponent
import com.mk.component.TextComponent
import com.mk.component.TextInputComponent
import com.mk.component.UiLibrary
import com.mk.theme.MainTheme

data class Colors(
    // Global colors
    val transparent: Color = Color(color = 0x00000000),

    // Dark Colors
    val darkNeutral1: Color = Color(color = 0xFF04030A),
    val darkNeutral2: Color = Color(color = 0xFF11111D),
    val darkNeutral3: Color = Color(color = 0xFF131320),
    val darkNeutral4: Color = Color(color = 0xFF1C1C27),
    val darkNeutral5: Color = Color(color = 0xFF2B2B36),
    val darkNeutral6: Color = Color(color = 0xFF383947),
    val darkNeutral7: Color = Color(color = 0xFF9D9DAA),
    val darkNeutral8: Color = Color(color = 0xFFFAF9F9),

    val darkPrimary1: Color = Color(color = 0xFF1E1A4E),
    val darkPrimary2: Color = Color(color = 0xFF4D41EA),
    val darkPrimary3: Color = Color(color = 0xFFC5D0FF),
    val darkPrimary4: Color = Color(color = 0xFFEDF2FF),

    val darkSecondary1: Color = Color(color = 0xFF0E1A15),
    val darkSecondary2: Color = Color(color = 0xFF4B8E6F),
    val darkSecondary3: Color = Color(color = 0xFF3EE4AA),

    val darkCritical1: Color = Color(color = 0xFF451815),
    val darkCritical2: Color = Color(color = 0xFFFF7585),

    val darkComplementary1: Color = Color(color = 0xFFC85250),
    val darkComplementary2: Color = Color(color = 0xFFA06204),
    val darkComplementary3: Color = Color(color = 0xFF1D741B),
    val darkComplementary4: Color = Color(color = 0xFF25388E),

    // Light Colors
    val lightNeutral1: Color = Color(color = 0xFFFAF9F9),
    val lightNeutral2: Color = Color(color = 0xFFF5F4F5),
    val lightNeutral3: Color = Color(color = 0xFFEEECEE),
    val lightNeutral4: Color = Color(color = 0xFFE5E4E7),
    val lightNeutral5: Color = Color(color = 0xFFD2D1D7),
    val lightNeutral6: Color = Color(color = 0xFF9D9DAA),
    val lightNeutral7: Color = Color(color = 0xFF6A6A7C),
    val lightNeutral8: Color = Color(color = 0xFF06050F),

    val lightPrimary1: Color = Color(color = 0xFFEDF2FF),
    val lightPrimary2: Color = Color(color = 0xFFC5D0FF),
    val lightPrimary3: Color = Color(color = 0xFF1E1A4E),
    val lightPrimary4: Color = Color(color = 0xFF0172D8),

    val lightSecondary1: Color = Color(color = 0xFFB5FFE5),
    val lightSecondary2: Color = Color(color = 0xFF72C69F),
    val lightSecondary3: Color = Color(color = 0xFF24CB90),

    val lightCritical1: Color = Color(color = 0xFFFDC5C2),
    val lightCritical2: Color = Color(color = 0xFFC71818),

    val lightComplementary1: Color = Color(color = 0xFFEB8684),
    val lightComplementary2: Color = Color(color = 0xFFE8A238),
    val lightComplementary3: Color = Color(color = 0xFF59B857),
    val lightComplementary4: Color = Color(color = 0xFF4E65CE),

    val sooRed: Color = Color(color = 0xFFFF001B),

    // Light scheme colors
    val mdThemeLightPrimary: Color = lightPrimary4,
    val mdThemeLightOnPrimary: Color = lightNeutral8,
    val mdThemeLightPrimaryContainer: Color = lightPrimary4,
    val mdThemeLightOnPrimaryContainer: Color = lightNeutral1,
    val mdThemeLightSecondary: Color = lightSecondary3,
    val mdThemeLightOnSecondary: Color = lightNeutral1,
    val mdThemeLightSecondaryContainer: Color = lightSecondary3,
    val mdThemeLightOnSecondaryContainer: Color = lightNeutral1,
    val mdThemeLightTertiary: Color = lightPrimary3,
    val mdThemeLightOnTertiary: Color = lightNeutral1,
    val mdThemeLightTertiaryContainer: Color = lightPrimary1,
    val mdThemeLightOnTertiaryContainer: Color = lightComplementary4,
    val mdThemeLightError: Color = lightCritical2,
    val mdThemeLightOnError: Color = lightNeutral8,
    val mdThemeLightErrorContainer: Color = lightCritical1,
    val mdThemeLightOnErrorContainer: Color = lightCritical2,
    val mdThemeLightBackground: Color = lightNeutral1,
    val mdThemeLightOnBackground: Color = lightNeutral8,
    val mdThemeLightSurface: Color = lightNeutral1,
    val mdThemeLightOnSurface: Color = lightNeutral8,
    val mdThemeLightSurfaceVariant: Color = lightNeutral2,
    val mdThemeLightOnSurfaceVariant: Color = lightNeutral7,
    val mdThemeLightOutline: Color = lightNeutral7,
    val mdThemeLightInverseOnSurface: Color = lightNeutral2,
    val mdThemeLightInverseSurface: Color = lightNeutral7,
    val mdThemeLightInversePrimary: Color = lightPrimary1,
    val mdThemeLightShadow: Color = lightNeutral8,
    val mdThemeLightSurfaceTint: Color = lightPrimary3,
    val mdThemeLightOutlineVariant: Color = lightNeutral5,
    val mdThemeLightScrim: Color = lightNeutral8,

    // Dark scheme colors
    val mdThemeDarkPrimary: Color = darkPrimary4,
    val mdThemeDarkOnPrimary: Color = darkNeutral8,
    val mdThemeDarkPrimaryContainer: Color = darkPrimary4,
    val mdThemeDarkOnPrimaryContainer: Color = darkNeutral1,
    val mdThemeDarkSecondary: Color = darkSecondary3,
    val mdThemeDarkOnSecondary: Color = darkNeutral1,
    val mdThemeDarkSecondaryContainer: Color = darkSecondary3,
    val mdThemeDarkOnSecondaryContainer: Color = darkNeutral1,
    val mdThemeDarkTertiary: Color = darkPrimary3,
    val mdThemeDarkOnTertiary: Color = darkNeutral1,
    val mdThemeDarkTertiaryContainer: Color = darkPrimary1,
    val mdThemeDarkOnTertiaryContainer: Color = darkComplementary4,
    val mdThemeDarkError: Color = darkCritical2,
    val mdThemeDarkOnError: Color = darkNeutral8,
    val mdThemeDarkErrorContainer: Color = darkCritical1,
    val mdThemeDarkOnErrorContainer: Color = darkCritical2,
    val mdThemeDarkBackground: Color = darkNeutral1,
    val mdThemeDarkOnBackground: Color = darkNeutral8,
    val mdThemeDarkSurface: Color = darkNeutral1,
    val mdThemeDarkOnSurface: Color = darkNeutral8,
    val mdThemeDarkSurfaceVariant: Color = darkNeutral2,
    val mdThemeDarkOnSurfaceVariant: Color = darkNeutral7,
    val mdThemeDarkOutline: Color = darkNeutral7,
    val mdThemeDarkInverseOnSurface: Color = darkNeutral2,
    val mdThemeDarkInverseSurface: Color = darkNeutral7,
    val mdThemeDarkInversePrimary: Color = darkPrimary1,
    val mdThemeDarkShadow: Color = darkNeutral8,
    val mdThemeDarkSurfaceTint: Color = darkPrimary3,
    val mdThemeDarkOutlineVariant: Color = darkNeutral5,
    val mdThemeDarkScrim: Color = darkNeutral8,
)

@OptIn(ExperimentalMaterial3Api::class)
@PreviewLightDark
@Composable
private fun ExampleScreen() {
    MainTheme {
        Surface {
            Column {
                UiLibrary.AppBarComponent.CenteredTopBar(
                    title = { UiLibrary.TextComponent.h1(text = "Example Screen") },
                    onBackClick = {},
                    actionContent = {
                        UiLibrary.ButtonComponent.primaryOutline(
                            text = "Next",
                            onClick = {},
                            modifier = Modifier.padding(horizontal = 10.dp),
                        )
                    },
                )

                Box(
                    modifier = Modifier.padding(10.dp),
                ) {
                    UiLibrary.TextInputComponent.DefaultTextInput(
                        value = "Title",
                        onValueChange = {},
                    )
                }

                Box(
                    modifier = Modifier.padding(10.dp),
                ) {
                    UiLibrary.TextInputComponent.DefaultTextInput(
                        value = "Title",
                        onValueChange = {},
                        isError = true,
                        supportingText = {
                            Text(text = "Some validation error")
                        },
                    )
                }

                Box(modifier = Modifier.padding(10.dp)) {
                    UiLibrary.TextInputComponent.DefaultTextInput(
                        value = "",
                        onValueChange = {},
                        placeholder = {
                            Text("Placeholder")
                        },
                    )
                }

                Box(modifier = Modifier.padding(10.dp)) {
                    UiLibrary.TextInputComponent.DefaultTextInput(
                        value = "long text",
                        onValueChange = {},
                        maxCharacters = 5,
                    )
                }

                Row(modifier = Modifier.padding(5.dp)) {
                    Button(onClick = { }, modifier = Modifier.padding(5.dp)) {
                        Text(text = "Button")
                    }

                    OutlinedButton(onClick = { }, modifier = Modifier.padding(5.dp)) {
                        Text(text = "OutlinedButton")
                    }

                    TextButton(onClick = { }, modifier = Modifier.padding(5.dp)) {
                        Text(text = "TextButton")
                    }
                }

                Row(modifier = Modifier.padding(5.dp)) {
                    ElevatedButton(onClick = { }, modifier = Modifier.padding(5.dp)) {
                        Text(text = "ElevatedButton")
                    }

                    FilledTonalButton(onClick = { }, modifier = Modifier.padding(5.dp)) {
                        Text(text = "FilledTonalButton")
                    }
                }

                Row(modifier = Modifier.padding(5.dp)) {
                    Button(
                        onClick = { },
                        modifier = Modifier.padding(5.dp),
                        colors = ButtonDefaults.buttonColors(
                            containerColor = MaterialTheme.colorScheme.errorContainer,
                            contentColor = MaterialTheme.colorScheme.onErrorContainer,
                        ),
                    ) {
                        Text(text = "Error Button")
                    }
                }
            }
        }
    }
}
