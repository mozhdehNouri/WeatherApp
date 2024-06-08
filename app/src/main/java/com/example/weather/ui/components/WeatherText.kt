package com.example.weather.ui.components

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.LocalTextStyle
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow


@Composable
fun WText(
    modifier: Modifier = Modifier,
    overflow: TextOverflow = TextOverflow.Visible,
    style: TextStyle = LocalTextStyle.current,
    maxLine: Int = 1,
    text: String,
    textColor: Color,
) {

    val textPadding = LocalTextPadding.current
    Text(
        text = text,
        style = style,
        color = textColor,
        textAlign = TextAlign.Start,
        overflow = overflow,
        maxLines = maxLine,
        modifier = modifier.padding(textPadding)
    )
}
