package com.example.weather.ui.components

import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.IconButtonColors
import androidx.compose.material3.IconButtonDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp

@Composable
fun WIconButton(
    modifier: Modifier = Modifier,
    icon: Int,
    tint: Color,
    buttonColor: IconButtonColors = IconButtonDefaults.iconButtonColors(),
    action: () -> Unit
) {
    val iconPadding = LocalIconPadding.current

    IconButton(
        onClick = action,
        modifier = modifier,
        colors = buttonColor
    ) {
        Icon(
            painter = painterResource(id = icon),
            tint = tint,
            contentDescription = null,
            modifier = Modifier
                .size(48.dp)
                .padding(iconPadding)
        )
    }
}