package com.example.weather.ui.components

import android.content.Context
import android.graphics.Bitmap
import android.graphics.Canvas
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.composed
import androidx.compose.ui.draw.drawWithCache
import androidx.compose.ui.draw.drawWithContent
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.BlendMode
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.drawscope.DrawScope
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.palette.graphics.Palette

@Composable
fun ImageBackgroundColorScrim(
    image: Int,
    color: Color,
    modifier: Modifier = Modifier,
) {
    ImageBackground(
        image = image,
        modifier = modifier,
        overlay = {
            drawRect(color)
        }
    )
}

@Composable
fun ImageBackgroundRadialGradientScrim(
    image: Int,
    colors: List<Color>,
    modifier: Modifier = Modifier,
) {
    ImageBackground(
        image = image,
        modifier = modifier,
        overlay = {
            val brush = Brush.radialGradient(
                colors = colors,
                center = Offset(0f, size.height),
                radius = size.width * 1.5f
            )
            drawRect(brush, blendMode = BlendMode.Multiply)
        }
    )
}

/**
 * Displays an image scaled 150% overlaid by [overlay]
 */
@Composable
fun ImageBackground(
    image: Int,
    overlay: DrawScope.() -> Unit,
    modifier: Modifier = Modifier,
) {
    Image(
        painter = painterResource(id = image),
        contentDescription = null,
        contentScale = ContentScale.Crop,
        modifier = modifier
            .fillMaxWidth()
            .drawWithCache {
                onDrawWithContent {
                    drawContent()
                    overlay()
                }
            }
    )
}

fun Modifier.horizontalGradientBackground(
    colors: List<Color>
) = gradientBackground(colors) { gradientColors, size ->
    Brush.horizontalGradient(
        colors = gradientColors,
        startX = 0f,
        endX = size.width
    )
}

private fun Modifier.gradientBackground(
    colors: List<Color>,
    brushProvider: (List<Color>, Size) -> Brush
): Modifier = composed {
    var size by remember { mutableStateOf(Size.Zero) }
    val gradient = remember(colors, size) { brushProvider(colors, size) }
    drawWithContent {
        size = this.size
        drawRect(brush = gradient)
        drawContent()
    }
}


fun Bitmap.generateDominantColorState(): Palette.Swatch =
    Palette.Builder(this)
        .resizeBitmapArea(0)
        .maximumColorCount(16)
        .generate()
        .swatches
        .maxByOrNull { swatch -> swatch.population }!!

fun convertVectorToBitmap(context: Context, id: Int): Bitmap? {
    val drawable = context.getDrawable(id)
    try {
        val bitmap = Bitmap.createBitmap(
            drawable!!.intrinsicWidth,
            drawable.intrinsicHeight,
            Bitmap.Config.ARGB_8888
        )

        val canvas = Canvas(bitmap)
        drawable.setBounds(0, 0, canvas.width, canvas.height)
        drawable.draw(canvas)
        return bitmap
    } catch (e: OutOfMemoryError) {

        return null
    }
}

