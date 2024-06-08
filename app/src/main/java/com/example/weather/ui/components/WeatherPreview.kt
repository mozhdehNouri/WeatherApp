package com.example.weather.ui.components

import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.ui.platform.LocalLayoutDirection
import androidx.compose.ui.unit.LayoutDirection
import com.example.weather.ui.theme.WeatherAppTheme

@Composable
fun WPreview(content: @Composable () -> Unit) {
    WeatherAppTheme {
        CompositionLocalProvider(
            LocalLayoutDirection provides LayoutDirection.Ltr,
        ) {
            content()
        }
    }
}