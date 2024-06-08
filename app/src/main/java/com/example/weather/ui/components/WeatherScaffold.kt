package com.example.weather.ui.components

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier

@Composable
fun WScaffold(
    modifier: Modifier = Modifier,
    content: @Composable (PaddingValues) -> Unit
) {
    val padding = LocalScaffoldPadding.current
    Scaffold(
        modifier = modifier
            .fillMaxSize()
            .padding(padding)
    ) {
        content(it)
    }
}
