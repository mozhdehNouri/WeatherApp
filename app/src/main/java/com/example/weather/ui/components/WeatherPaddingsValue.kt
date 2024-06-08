package com.example.weather.ui.components

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.runtime.staticCompositionLocalOf
import androidx.compose.ui.unit.dp


val LocalButtonPadding = staticCompositionLocalOf { ButtonContentPadding }
val LocalScaffoldPadding =
    staticCompositionLocalOf { ScaffoldContentPadding }
val LocalTextPadding = staticCompositionLocalOf { TextContentPadding }
val LocalRowPadding = staticCompositionLocalOf { RowContentPadding }
val LocalColumnPadding = staticCompositionLocalOf { ColumnContentPadding }
val LocalColumnParentPadding =
    staticCompositionLocalOf { ColumnParentPadding }
val LocalImagePadding = staticCompositionLocalOf { ImageContentPadding }
val LocalIconPadding = staticCompositionLocalOf { IconContentPadding }
val LocalVerticalSpacerPadding =
    staticCompositionLocalOf { VerticalSpacerPadding }
val LocalLazyRowContentPadding =
    staticCompositionLocalOf { LazyRowContentPadding }

val LazyRowContentPadding = 12.dp
val VerticalSpacerPadding = PaddingValues(vertical = 8.dp)
val ScaffoldContentPadding =
    PaddingValues(start = 16.dp, top = 8.dp, end = 16.dp, bottom = 8.dp)
private val ButtonContentPadding =
    PaddingValues(horizontal = 4.dp, vertical = 4.dp)
private val TextContentPadding = PaddingValues(vertical = 4.dp)
private val ColumnParentPadding =
    PaddingValues(horizontal = 12.dp, vertical = 8.dp)
private val ColumnContentPadding =
    PaddingValues(start = 12.dp, top = 4.dp, end = 0.dp, bottom = 4.dp)
private val ImageContentPadding =
    PaddingValues(start = 3.dp, top = 0.dp, end = 3.dp, bottom = 0.dp)
private val IconContentPadding = PaddingValues(4.dp)
private val RowContentPadding =
    PaddingValues(start = 0.dp, top = 8.dp, end = 0.dp, bottom = 8.dp)