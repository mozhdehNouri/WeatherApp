package com.example.weather.ui.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier

@Composable
inline fun WColumn_Parent(
    modifier: Modifier = Modifier,
    header: @Composable () -> Unit,
    content: @Composable ColumnScope.() -> Unit,
    bottom: @Composable () -> Unit
) {
    val columnPadding = LocalColumnParentPadding.current

    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(columnPadding)
    ) {
        header()
        content()
        bottom()
    }
}