package com.example.weather.ui.components

import androidx.compose.animation.core.InfiniteRepeatableSpec
import androidx.compose.animation.core.LinearEasing
import androidx.compose.animation.core.RepeatMode
import androidx.compose.animation.core.StartOffset
import androidx.compose.animation.core.StartOffsetType
import androidx.compose.animation.core.VectorConverter
import androidx.compose.animation.core.animateFloat
import androidx.compose.animation.core.animateValue
import androidx.compose.animation.core.infiniteRepeatable
import androidx.compose.animation.core.rememberInfiniteTransition
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.BoxWithConstraints
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.LocalContentColor
import androidx.compose.material3.contentColorFor
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableFloatStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.text.TextStyle

const val CycleDuration = 1_000

@Composable
fun WLoading_Text(
    modifier: Modifier = Modifier,
    cycleDuration: Int = CycleDuration,
    text: String,
    style: TextStyle,
    textColor: Color
) {

    val transition = rememberInfiniteTransition(label = "Dots Transition")
    val visibleDotsCount = transition.animateValue(
        initialValue = 0,
        targetValue = 4,
        typeConverter = Int.VectorConverter,
        animationSpec = infiniteRepeatable(
            animation = tween(
                durationMillis = cycleDuration,
                easing = LinearEasing
            ),
            repeatMode = RepeatMode.Restart
        ), label = "Visible Dots Count"
    )

    WText(
        text = text + ".".repeat(visibleDotsCount.value),
        modifier = modifier,
        style = style,
        textColor = textColor
    )
}

@Composable
fun WHorizontalLoadingCircles(
    modifier: Modifier = Modifier,
    color: Color = contentColorFor(backgroundColor = LocalContentColor.current),
    radius: Int,
    padding: Int,
    count: Int
) {

    val transition = rememberInfiniteTransition(label = "transition")
    val offsetList = remember { mutableListOf<State<Float>>() }
    val baseDelay = 200

    if (offsetList.isEmpty()) {
        for (i in 0 until count) {
            offsetList.add(
                transition.animateFloat(
                    initialValue = 0f,
                    targetValue = radius.toFloat(),
                    animationSpec = InfiniteRepeatableSpec(
                        animation = tween(count * baseDelay),
                        repeatMode = RepeatMode.Reverse,
                        initialStartOffset = StartOffset(
                            offsetMillis = i * baseDelay,
                            offsetType = StartOffsetType.FastForward
                        )
                    ),
                    label = "offset"
                )
            )
        }
    }

    BoxWithConstraints(modifier) {
        val density = LocalDensity.current
        val length =
            remember { radius / 2 + (count - 1) * (padding + radius) }
        val startOffset = remember { mutableFloatStateOf(0f) }

        LaunchedEffect(length) {
            with(density) {
                startOffset.floatValue = (maxWidth.toPx() - length) / 2
            }
        }

        Canvas(
            modifier = Modifier
                .fillMaxWidth()
                .align(Alignment.Center),
            onDraw = {
                for (i in 0 until count) {
                    drawCircle(
                        color = color,
                        radius = offsetList[i].value,
                        center = Offset(
                            x = startOffset.floatValue + (2 * radius * i) + (padding * i),
                            y = 0f
                        )
                    )
                }
            }
        )
    }
}
