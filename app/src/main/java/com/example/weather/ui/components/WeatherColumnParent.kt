package com.example.weather.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier

@Composable
inline fun WColumn_Parent(
    header: @Composable () -> Unit,
    content: @Composable ColumnScope.() -> Unit,
    bottom: @Composable () -> Unit,
) {
    val columnPadding = LocalColumnParentPadding.current
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(columnPadding)
            .background(MaterialTheme.colorScheme.background),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        header()
        content()
        bottom()
    }
}