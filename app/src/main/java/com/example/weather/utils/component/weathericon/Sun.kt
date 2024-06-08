package com.example.weather.utils.component.weathericon

import androidx.compose.animation.core.RepeatMode
import androidx.compose.animation.core.animateFloat
import androidx.compose.animation.core.infiniteRepeatable
import androidx.compose.animation.core.rememberInfiniteTransition
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.graphics.drawscope.Fill
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.weather.ui.theme.clearSky_dark_primary
import com.example.weather.ui.theme.clearSky_dark_tertiaryContainer
import kotlin.math.cos
import kotlin.math.sin

@Composable
fun AnimatableSun(modifier: Modifier = Modifier) {
    val transition = rememberInfiniteTransition(label = "sun_transition")

    val animateTween by transition.animateFloat(
        initialValue = 0f,
        targetValue = 360f,
        animationSpec = infiniteRepeatable(
            tween(5000),
            RepeatMode.Reverse
        ), label = "sun_tween"
    )

    Canvas(modifier.rotate(animateTween)) {

        val radius = size.width / 5
        val stroke = size.width / 15
        val centerOffset = Offset(size.width / 10, size.width / 10)

        // draw stroke circle
//        drawCircle(
//            color = Color.Black,
//            radius = radius + stroke / 2,
//            style = Stroke(width = stroke),
//            center = center + centerOffset
//        )
        // draw circle
        drawCircle(
            color = clearSky_dark_primary,
            radius = radius,
            style = Fill,
            center = center + centerOffset
        )

        // draw line

        val lineLength = radius * 0.6f
        val lineOffset = radius * 1.8f
        (0..7).forEach { i ->

            val radians = Math.toRadians(i * 45.0)

            val offsetX = lineOffset * cos(radians).toFloat()
            val offsetY = lineOffset * sin(radians).toFloat()

            val x1 = size.width / 2 + offsetX + centerOffset.x
            val x2 = x1 + lineLength * cos(radians).toFloat()

            val y1 = size.height / 2 + offsetY + centerOffset.y
            val y2 = y1 + lineLength * sin(radians).toFloat()

            drawLine(
                color = clearSky_dark_tertiaryContainer,
                start = Offset(x1, y1),
                end = Offset(x2, y2),
                strokeWidth = stroke,
                cap = StrokeCap.Round
            )
        }
    }
}

@Composable
fun Sun(modifier: Modifier = Modifier) {

    Canvas(modifier) {

        val radius = size.width / 6
        val stroke = size.width / 20

        // draw circle
        drawCircle(
            color = Color.Black,
            radius = radius + stroke / 2,
            style = Stroke(width = stroke),
        )
        drawCircle(
            color = Color.White,
            radius = radius,
            style = Fill,
        )

        // draw line

        val lineLength = radius * 0.2f
        val lineOffset = radius * 1.8f
        (0..7).forEach { i ->

            val radians = Math.toRadians(i * 45.0)

            val offsetX = lineOffset * cos(radians).toFloat()
            val offsetY = lineOffset * sin(radians).toFloat()

            val x1 = size.width / 2 + offsetX
            val x2 = x1 + lineLength * cos(radians).toFloat()

            val y1 = size.height / 2 + offsetY
            val y2 = y1 + lineLength * sin(radians).toFloat()

            drawLine(
                color = Color.Black,
                start = Offset(x1, y1),
                end = Offset(x2, y2),
                strokeWidth = stroke,
                cap = StrokeCap.Round
            )
        }
    }
}

@Preview
@Composable
fun PreviewAnimatableSun() {
    AnimatableSun(Modifier.size(100.dp))
}
