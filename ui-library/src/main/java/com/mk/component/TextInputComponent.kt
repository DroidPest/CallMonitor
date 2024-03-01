package com.mk.component

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.KeyboardCapitalization
import androidx.compose.ui.tooling.preview.PreviewLightDark
import com.mk.theme.LocalTheme
import com.mk.theme.MainTheme

@Suppress("LongParameterList", "TooManyFunctions")
interface TextInputComponent {

    @Composable
    fun DefaultTextInput(
        modifier: Modifier = Modifier,
        value: String,
        onValueChange: (String) -> Unit,
        maxLines: Int = 1,
        enabled: Boolean = true,
        readOnly: Boolean = false,
        label: @Composable (() -> Unit)? = null,
        placeholder: @Composable (() -> Unit)? = null,
        leadingIcon: @Composable (() -> Unit)? = null,
        trailingIcon: @Composable (() -> Unit)? = null,
        prefix: @Composable (() -> Unit)? = null,
        suffix: @Composable (() -> Unit)? = null,
        supportingText: @Composable (() -> Unit)? = null,
        isError: Boolean = false,
        showCounter: Boolean = true,
        maxCharacters: Int = 100,
    ) {
        val theme = LocalTheme.current

        var currentText by remember(value) { mutableStateOf(value) }
        val totalCharacters by remember(currentText) { mutableIntStateOf(currentText.length) }
        val remainingCharacters = maxCharacters - currentText.length

        Row {
            TextField(
                modifier = modifier
                    .fillMaxWidth()
                    .weight(1f),
                value = currentText,
                onValueChange = {
                    currentText = it
                    if (maxCharacters >= totalCharacters) {
                        onValueChange(it)
                    }
                },
                keyboardOptions = KeyboardOptions.Default.copy(
                    capitalization = KeyboardCapitalization.Sentences,
                ),
                maxLines = maxLines,
                enabled = enabled,
                readOnly = readOnly,
                label = label,
                placeholder = placeholder,
                leadingIcon = leadingIcon,
                trailingIcon = trailingIcon,
                prefix = prefix,
                suffix = {
                    if (showCounter) {
                        Text(
                            text = "$totalCharacters/$maxCharacters",
                            modifier = Modifier
                                .align(Alignment.CenterVertically),
                            color = if (remainingCharacters < 0) {
                                MaterialTheme.colorScheme.onError
                            } else { MaterialTheme.colorScheme.onSurface },
                            style = theme.fontStyle.textInputCounter,
                        )
                    }
                },
                supportingText = supportingText,
                isError = isError,
                textStyle = theme.fontStyle.textInput1,
                colors = TextFieldDefaults.colors(
                    focusedContainerColor = MaterialTheme.colorScheme.background,
                    unfocusedContainerColor = MaterialTheme.colorScheme.background,
                    errorContainerColor = MaterialTheme.colorScheme.background,
                ),
            )
        }
    }
}

val UiLibrary.TextInputComponent
    get() = object : TextInputComponent {}

@PreviewLightDark
@Composable
private fun DefaultTextInputNoValuePreview() {
    MainTheme {
        Column {
            UiLibrary.TextInputComponent.DefaultTextInput(
                value = "",
                label = {
                    Text(text = "Example")
                },
                onValueChange = {},
            )
        }
    }
}

@PreviewLightDark
@Composable
private fun DefaultTextInputPreview() {
    MainTheme {
        Column {
            UiLibrary.TextInputComponent.DefaultTextInput(
                value = "Some text here",
                label = {
                    Text(text = "Example")
                },
                onValueChange = {},
            )
        }
    }
}

@PreviewLightDark
@Composable
private fun DefaultTextInputPreviewLimitChars() {
    MainTheme {
        Column {
            UiLibrary.TextInputComponent.DefaultTextInput(
                value = "Some text here",
                label = {
                    Text(text = "Example")
                },
                onValueChange = {},
                maxCharacters = 10,
            )
        }
    }
}

@PreviewLightDark
@Composable
private fun DefaultTextInputNoLabelPreview() {
    MainTheme {
        Column {
            UiLibrary.TextInputComponent.DefaultTextInput(
                value = "Some text here",
                onValueChange = {},
            )
        }
    }
}

@PreviewLightDark
@Composable
private fun DefaultTextInputMultilinePreview() {
    MainTheme {
        Column {
            UiLibrary.TextInputComponent.DefaultTextInput(
                value = "Sample text for a max value of 1000",
                maxCharacters = 1000,
                maxLines = 10,
                onValueChange = {},
            )
        }
    }
}

@PreviewLightDark
@Composable
private fun DefaultTextInputErrorPreview() {
    MainTheme {
        Column {
            UiLibrary.TextInputComponent.DefaultTextInput(
                value = "Sample text for a max value of 10",
                maxCharacters = 10,
                maxLines = 1,
                onValueChange = {},
                isError = true,
                supportingText = {
                    Text(text = "Some error validation message")
                },
            )
        }
    }
}
