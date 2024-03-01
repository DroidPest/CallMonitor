package com.mk.component

import androidx.annotation.StringRes
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextLayoutResult
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.PreviewLightDark
import androidx.compose.ui.unit.dp
import com.mk.theme.LocalTheme
import com.mk.theme.MainTheme
import com.mk.ui_library.R

@Suppress("LongParameterList", "TooManyFunctions")
interface TextComponent {

    @Composable
    private fun defaultText(
        text: String,
        modifier: Modifier,
        textDecoration: TextDecoration?,
        textAlign: TextAlign?,
        overflow: TextOverflow,
        softWrap: Boolean,
        maxLines: Int,
        minLines: Int,
        onTextLayout: (TextLayoutResult) -> Unit,
        style: TextStyle,
        color: Color = Color.Unspecified,
    ) {
        Text(
            text = text,
            modifier = modifier,
            textDecoration = textDecoration,
            textAlign = textAlign,
            overflow = overflow,
            softWrap = softWrap,
            maxLines = maxLines,
            minLines = minLines,
            onTextLayout = onTextLayout,
            style = style,
            color = color,
        )
    }

    @Composable
    fun primaryButtonText(
        @StringRes text: Int,
        modifier: Modifier = Modifier,
        textDecoration: TextDecoration? = null,
        textAlign: TextAlign? = null,
        overflow: TextOverflow = TextOverflow.Clip,
        softWrap: Boolean = true,
        maxLines: Int = Int.MAX_VALUE,
        minLines: Int = 1,
        onTextLayout: (TextLayoutResult) -> Unit = {},
    ) {
        defaultText(
            text = stringResource(id = text),
            modifier = modifier,
            textDecoration = textDecoration,
            textAlign = textAlign,
            overflow = overflow,
            softWrap = softWrap,
            maxLines = maxLines,
            minLines = minLines,
            onTextLayout = onTextLayout,
            style = LocalTheme.current.fontStyle.body1,
        )
    }

    @Composable
    fun button1(
        @StringRes text: Int,
        modifier: Modifier = Modifier,
        textDecoration: TextDecoration? = null,
        textAlign: TextAlign? = null,
        overflow: TextOverflow = TextOverflow.Clip,
        softWrap: Boolean = true,
        maxLines: Int = Int.MAX_VALUE,
        minLines: Int = 1,
        color: Color = Color.Unspecified,
        onTextLayout: (TextLayoutResult) -> Unit = {},
    ) {
        defaultText(
            text = stringResource(id = text),
            modifier = modifier,
            textDecoration = textDecoration,
            textAlign = textAlign,
            overflow = overflow,
            softWrap = softWrap,
            maxLines = maxLines,
            minLines = minLines,
            onTextLayout = onTextLayout,
            color = color,
            style = LocalTheme.current.fontStyle.button1,
        )
    }

    @Composable
    fun h1(
        @StringRes text: Int,
        modifier: Modifier = Modifier,
        textDecoration: TextDecoration? = null,
        textAlign: TextAlign? = null,
        overflow: TextOverflow = TextOverflow.Clip,
        softWrap: Boolean = true,
        maxLines: Int = Int.MAX_VALUE,
        minLines: Int = 1,
        color: Color = Color.Unspecified,
        onTextLayout: (TextLayoutResult) -> Unit = {},
    ) {
        defaultText(
            text = stringResource(id = text),
            modifier = modifier,
            textDecoration = textDecoration,
            textAlign = textAlign,
            overflow = overflow,
            softWrap = softWrap,
            maxLines = maxLines,
            minLines = minLines,
            onTextLayout = onTextLayout,
            color = color,
            style = LocalTheme.current.fontStyle.h1,
        )
    }

    @Composable
    fun h2(
        @StringRes text: Int,
        modifier: Modifier = Modifier,
        textDecoration: TextDecoration? = null,
        textAlign: TextAlign? = null,
        overflow: TextOverflow = TextOverflow.Clip,
        softWrap: Boolean = true,
        maxLines: Int = Int.MAX_VALUE,
        minLines: Int = 1,
        color: Color = Color.Unspecified,
        onTextLayout: (TextLayoutResult) -> Unit = {},
    ) {
        defaultText(
            text = stringResource(id = text),
            modifier = modifier,
            textDecoration = textDecoration,
            textAlign = textAlign,
            overflow = overflow,
            softWrap = softWrap,
            maxLines = maxLines,
            minLines = minLines,
            onTextLayout = onTextLayout,
            color = color,
            style = LocalTheme.current.fontStyle.h2,
        )
    }

    @Composable
    fun h3(
        @StringRes text: Int,
        modifier: Modifier = Modifier,
        textDecoration: TextDecoration? = null,
        textAlign: TextAlign? = null,
        overflow: TextOverflow = TextOverflow.Clip,
        softWrap: Boolean = true,
        maxLines: Int = Int.MAX_VALUE,
        minLines: Int = 1,
        color: Color = Color.Unspecified,
        onTextLayout: (TextLayoutResult) -> Unit = {},
    ) {
        defaultText(
            text = stringResource(id = text),
            modifier = modifier,
            textDecoration = textDecoration,
            textAlign = textAlign,
            overflow = overflow,
            softWrap = softWrap,
            maxLines = maxLines,
            minLines = minLines,
            onTextLayout = onTextLayout,
            color = color,
            style = LocalTheme.current.fontStyle.h3,
        )
    }

    @Composable
    fun body1(
        @StringRes text: Int,
        modifier: Modifier = Modifier,
        textDecoration: TextDecoration? = null,
        textAlign: TextAlign? = null,
        overflow: TextOverflow = TextOverflow.Clip,
        softWrap: Boolean = true,
        maxLines: Int = Int.MAX_VALUE,
        minLines: Int = 1,
        color: Color = Color.Unspecified,
        onTextLayout: (TextLayoutResult) -> Unit = {},
    ) {
        defaultText(
            text = stringResource(id = text),
            modifier = modifier,
            textDecoration = textDecoration,
            textAlign = textAlign,
            overflow = overflow,
            softWrap = softWrap,
            maxLines = maxLines,
            minLines = minLines,
            onTextLayout = onTextLayout,
            color = color,
            style = LocalTheme.current.fontStyle.body1,
        )
    }

    @Composable
    fun body2(
        @StringRes text: Int,
        modifier: Modifier = Modifier,
        textDecoration: TextDecoration? = null,
        textAlign: TextAlign? = null,
        overflow: TextOverflow = TextOverflow.Clip,
        softWrap: Boolean = true,
        maxLines: Int = Int.MAX_VALUE,
        minLines: Int = 1,
        color: Color = Color.Unspecified,
        onTextLayout: (TextLayoutResult) -> Unit = {},
    ) {
        defaultText(
            text = stringResource(id = text),
            modifier = modifier,
            textDecoration = textDecoration,
            textAlign = textAlign,
            overflow = overflow,
            softWrap = softWrap,
            maxLines = maxLines,
            minLines = minLines,
            onTextLayout = onTextLayout,
            color = color,
            style = LocalTheme.current.fontStyle.body2,
        )
    }

    @Composable
    fun body3(
        @StringRes text: Int,
        modifier: Modifier = Modifier,
        textDecoration: TextDecoration? = null,
        textAlign: TextAlign? = null,
        overflow: TextOverflow = TextOverflow.Clip,
        softWrap: Boolean = true,
        maxLines: Int = Int.MAX_VALUE,
        minLines: Int = 1,
        color: Color = Color.Unspecified,
        onTextLayout: (TextLayoutResult) -> Unit = {},
    ) {
        defaultText(
            text = stringResource(id = text),
            modifier = modifier,
            textDecoration = textDecoration,
            textAlign = textAlign,
            overflow = overflow,
            softWrap = softWrap,
            maxLines = maxLines,
            minLines = minLines,
            onTextLayout = onTextLayout,
            color = color,
            style = LocalTheme.current.fontStyle.body3,
        )
    }

    @Composable
    fun caption1(
        @StringRes text: Int,
        modifier: Modifier = Modifier,
        textDecoration: TextDecoration? = null,
        textAlign: TextAlign? = null,
        overflow: TextOverflow = TextOverflow.Clip,
        softWrap: Boolean = true,
        maxLines: Int = Int.MAX_VALUE,
        minLines: Int = 1,
        color: Color = Color.Unspecified,
        onTextLayout: (TextLayoutResult) -> Unit = {},
    ) {
        defaultText(
            text = stringResource(id = text),
            modifier = modifier,
            textDecoration = textDecoration,
            textAlign = textAlign,
            overflow = overflow,
            softWrap = softWrap,
            maxLines = maxLines,
            minLines = minLines,
            onTextLayout = onTextLayout,
            color = color,
            style = LocalTheme.current.fontStyle.caption1,
        )
    }

    @Composable
    fun micro1(
        @StringRes text: Int,
        modifier: Modifier = Modifier,
        textDecoration: TextDecoration? = null,
        textAlign: TextAlign? = null,
        overflow: TextOverflow = TextOverflow.Clip,
        softWrap: Boolean = true,
        maxLines: Int = Int.MAX_VALUE,
        minLines: Int = 1,
        color: Color = Color.Unspecified,
        onTextLayout: (TextLayoutResult) -> Unit = {},
    ) {
        defaultText(
            text = stringResource(id = text),
            modifier = modifier,
            textDecoration = textDecoration,
            textAlign = textAlign,
            overflow = overflow,
            softWrap = softWrap,
            maxLines = maxLines,
            minLines = minLines,
            onTextLayout = onTextLayout,
            color = color,
            style = LocalTheme.current.fontStyle.micro1,
        )
    }

    @Composable
    fun primaryButtonText(
        text: String,
        modifier: Modifier = Modifier,
        textDecoration: TextDecoration? = null,
        textAlign: TextAlign? = null,
        overflow: TextOverflow = TextOverflow.Clip,
        softWrap: Boolean = true,
        maxLines: Int = Int.MAX_VALUE,
        minLines: Int = 1,
        onTextLayout: (TextLayoutResult) -> Unit = {},
    ) {
        defaultText(
            text = text,
            modifier = modifier,
            textDecoration = textDecoration,
            textAlign = textAlign,
            overflow = overflow,
            softWrap = softWrap,
            maxLines = maxLines,
            minLines = minLines,
            onTextLayout = onTextLayout,
            style = LocalTheme.current.fontStyle.body1,
        )
    }

    @Composable
    fun button1(
        text: String,
        modifier: Modifier = Modifier,
        textDecoration: TextDecoration? = null,
        textAlign: TextAlign? = null,
        overflow: TextOverflow = TextOverflow.Clip,
        softWrap: Boolean = true,
        maxLines: Int = Int.MAX_VALUE,
        minLines: Int = 1,
        color: Color = Color.Unspecified,
        onTextLayout: (TextLayoutResult) -> Unit = {},
    ) {
        defaultText(
            text = text,
            modifier = modifier,
            textDecoration = textDecoration,
            textAlign = textAlign,
            overflow = overflow,
            softWrap = softWrap,
            maxLines = maxLines,
            minLines = minLines,
            onTextLayout = onTextLayout,
            color = color,
            style = LocalTheme.current.fontStyle.button1,
        )
    }

    @Composable
    fun h1(
        text: String,
        modifier: Modifier = Modifier,
        textDecoration: TextDecoration? = null,
        textAlign: TextAlign? = null,
        overflow: TextOverflow = TextOverflow.Clip,
        softWrap: Boolean = true,
        maxLines: Int = Int.MAX_VALUE,
        minLines: Int = 1,
        color: Color = Color.Unspecified,
        onTextLayout: (TextLayoutResult) -> Unit = {},
    ) {
        defaultText(
            text = text,
            modifier = modifier,
            textDecoration = textDecoration,
            textAlign = textAlign,
            overflow = overflow,
            softWrap = softWrap,
            maxLines = maxLines,
            minLines = minLines,
            onTextLayout = onTextLayout,
            color = color,
            style = LocalTheme.current.fontStyle.h1,
        )
    }

    @Composable
    fun h2(
        text: String,
        modifier: Modifier = Modifier,
        textDecoration: TextDecoration? = null,
        textAlign: TextAlign? = null,
        overflow: TextOverflow = TextOverflow.Clip,
        softWrap: Boolean = true,
        maxLines: Int = Int.MAX_VALUE,
        minLines: Int = 1,
        color: Color = Color.Unspecified,
        onTextLayout: (TextLayoutResult) -> Unit = {},
    ) {
        defaultText(
            text = text,
            modifier = modifier,
            textDecoration = textDecoration,
            textAlign = textAlign,
            overflow = overflow,
            softWrap = softWrap,
            maxLines = maxLines,
            minLines = minLines,
            onTextLayout = onTextLayout,
            color = color,
            style = LocalTheme.current.fontStyle.h2,
        )
    }

    @Composable
    fun h3(
        text: String,
        modifier: Modifier = Modifier,
        textDecoration: TextDecoration? = null,
        textAlign: TextAlign? = null,
        overflow: TextOverflow = TextOverflow.Clip,
        softWrap: Boolean = true,
        maxLines: Int = Int.MAX_VALUE,
        minLines: Int = 1,
        color: Color = Color.Unspecified,
        onTextLayout: (TextLayoutResult) -> Unit = {},
    ) {
        defaultText(
            text = text,
            modifier = modifier,
            textDecoration = textDecoration,
            textAlign = textAlign,
            overflow = overflow,
            softWrap = softWrap,
            maxLines = maxLines,
            minLines = minLines,
            onTextLayout = onTextLayout,
            color = color,
            style = LocalTheme.current.fontStyle.h3,
        )
    }

    @Composable
    fun body1(
        text: String,
        modifier: Modifier = Modifier,
        textDecoration: TextDecoration? = null,
        textAlign: TextAlign? = null,
        overflow: TextOverflow = TextOverflow.Clip,
        softWrap: Boolean = true,
        maxLines: Int = Int.MAX_VALUE,
        minLines: Int = 1,
        color: Color = Color.Unspecified,
        onTextLayout: (TextLayoutResult) -> Unit = {},
    ) {
        defaultText(
            text = text,
            modifier = modifier,
            textDecoration = textDecoration,
            textAlign = textAlign,
            overflow = overflow,
            softWrap = softWrap,
            maxLines = maxLines,
            minLines = minLines,
            onTextLayout = onTextLayout,
            color = color,
            style = LocalTheme.current.fontStyle.body1,
        )
    }

    @Composable
    fun body2(
        text: String,
        modifier: Modifier = Modifier,
        textDecoration: TextDecoration? = null,
        textAlign: TextAlign? = null,
        overflow: TextOverflow = TextOverflow.Clip,
        softWrap: Boolean = true,
        maxLines: Int = Int.MAX_VALUE,
        minLines: Int = 1,
        color: Color = Color.Unspecified,
        onTextLayout: (TextLayoutResult) -> Unit = {},
    ) {
        defaultText(
            text = text,
            modifier = modifier,
            textDecoration = textDecoration,
            textAlign = textAlign,
            overflow = overflow,
            softWrap = softWrap,
            maxLines = maxLines,
            minLines = minLines,
            onTextLayout = onTextLayout,
            color = color,
            style = LocalTheme.current.fontStyle.body2,
        )
    }

    @Composable
    fun body3(
        text: String,
        modifier: Modifier = Modifier,
        textDecoration: TextDecoration? = null,
        textAlign: TextAlign? = null,
        overflow: TextOverflow = TextOverflow.Clip,
        softWrap: Boolean = true,
        maxLines: Int = Int.MAX_VALUE,
        minLines: Int = 1,
        color: Color = Color.Unspecified,
        onTextLayout: (TextLayoutResult) -> Unit = {},
    ) {
        defaultText(
            text = text,
            modifier = modifier,
            textDecoration = textDecoration,
            textAlign = textAlign,
            overflow = overflow,
            softWrap = softWrap,
            maxLines = maxLines,
            minLines = minLines,
            onTextLayout = onTextLayout,
            color = color,
            style = LocalTheme.current.fontStyle.body3,
        )
    }

    @Composable
    fun caption1(
        text: String,
        modifier: Modifier = Modifier,
        textDecoration: TextDecoration? = null,
        textAlign: TextAlign? = null,
        overflow: TextOverflow = TextOverflow.Clip,
        softWrap: Boolean = true,
        maxLines: Int = Int.MAX_VALUE,
        minLines: Int = 1,
        color: Color = Color.Unspecified,
        onTextLayout: (TextLayoutResult) -> Unit = {},
    ) {
        defaultText(
            text = text,
            modifier = modifier,
            textDecoration = textDecoration,
            textAlign = textAlign,
            overflow = overflow,
            softWrap = softWrap,
            maxLines = maxLines,
            minLines = minLines,
            onTextLayout = onTextLayout,
            color = color,
            style = LocalTheme.current.fontStyle.caption1,
        )
    }

    @Composable
    fun micro1(
        text: String,
        modifier: Modifier = Modifier,
        textDecoration: TextDecoration? = null,
        textAlign: TextAlign? = null,
        overflow: TextOverflow = TextOverflow.Clip,
        softWrap: Boolean = true,
        maxLines: Int = Int.MAX_VALUE,
        minLines: Int = 1,
        color: Color = Color.Unspecified,
        onTextLayout: (TextLayoutResult) -> Unit = {},
    ) {
        defaultText(
            text = text,
            modifier = modifier,
            textDecoration = textDecoration,
            textAlign = textAlign,
            overflow = overflow,
            softWrap = softWrap,
            maxLines = maxLines,
            minLines = minLines,
            onTextLayout = onTextLayout,
            color = color,
            style = LocalTheme.current.fontStyle.micro1,
        )
    }
}

val UiLibrary.TextComponent
    get() = object : TextComponent {}

@PreviewLightDark
@Composable
private fun TextComponentPreview() {
    with(UiLibrary.TextComponent) {
        MainTheme {
            Surface {
                Column(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(16.dp),
                    verticalArrangement = Arrangement.spacedBy(16.dp),
                    horizontalAlignment = Alignment.CenterHorizontally,
                ) {
                    h1(R.string.text_component_h1_sample)
                    h2(R.string.text_component_h2_sample)
                    h3(R.string.text_component_h3_sample)
                    body1(R.string.text_component_body1_sample)
                    body2(R.string.text_component_body2_sample)
                    body3(R.string.text_component_body3_sample)
                    caption1(R.string.text_component_caption1_sample)
                    micro1(R.string.text_component_micro1_sample)
                }
            }
        }
    }
}
