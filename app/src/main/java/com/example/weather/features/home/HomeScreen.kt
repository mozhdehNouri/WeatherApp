package com.example.weather.features.home

import androidx.compose.animation.core.LinearEasing
import androidx.compose.animation.core.RepeatMode
import androidx.compose.animation.core.animateFloat
import androidx.compose.animation.core.infiniteRepeatable
import androidx.compose.animation.core.rememberInfiniteTransition
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.LinearGradientShader
import androidx.compose.ui.graphics.Shader
import androidx.compose.ui.graphics.ShaderBrush
import androidx.compose.ui.graphics.TileMode
import androidx.compose.ui.tooling.preview.Preview
import com.example.weather.ui.theme.cloudy_dark_primaryContainer
import com.example.weather.ui.theme.cloudy_light_primary
import com.example.weather.ui.theme.drizzle_dark_tertiaryContainer
import okhttp3.internal.immutableListOf

@Composable
fun HomeScreen() {

}

@Composable
private fun HomeRouter() {

}

@Composable
private fun HomeStates() {

}

@Composable
private fun HomeUI(
    modifier: Modifier = Modifier
) {
    val brushColors = immutableListOf<Color>(
        cloudy_light_primary,
        cloudy_dark_primaryContainer,
        drizzle_dark_tertiaryContainer
    )

    val infiniteTransition = rememberInfiniteTransition(label = "")

    val offset by infiniteTransition.animateFloat(
        initialValue = 0f,
        targetValue = 1f,
        animationSpec = infiniteRepeatable(
            animation = tween(durationMillis = 1500, easing = LinearEasing),
            repeatMode = RepeatMode.Reverse
        ), label = ""
    )


    val brush = remember(offset) {
        object : ShaderBrush() {
            override fun createShader(size: Size): Shader {
                val widthOffset = size.width * offset
                val heightOffset = size.height * offset
                return LinearGradientShader(
                    colors = brushColors,
                    from = Offset(widthOffset, heightOffset),
                    to = Offset(widthOffset + size.width, heightOffset + size.height),
                    tileMode = TileMode.Clamp
                )
            }
        }
    }

    Box(
        modifier = modifier
            .fillMaxSize()
            .background(brush = brush)
    ) {


    }
}

@Preview
@Composable
fun HomeScreenPreview() {
    HomeUI()
}