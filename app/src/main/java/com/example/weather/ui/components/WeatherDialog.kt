package com.example.weather.ui.components

import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.DialogProperties

enum class DialogType {
    Exit,
    Continue
}

@Composable
fun WDialog(
    type: DialogType,
    text: String,
    confirmTextButton: String = "",
    cancelButtonText: String = "",
    textStyle: Pair<Color, TextStyle> = Pair(
        MaterialTheme.colorScheme.onSurface,
        MaterialTheme.typography.titleMedium
    ),
    confirmStyle: Pair<Color, TextStyle> = Pair(
        MaterialTheme.colorScheme.onSurface,
        MaterialTheme.typography.bodyMedium
    ),
    cancelStyle: Pair<Color, TextStyle> = Pair(
        MaterialTheme.colorScheme.onSurface,
        MaterialTheme.typography.bodyMedium
    ),
    containerColor: Pair<Color, Color> = Pair(
        MaterialTheme.colorScheme.onSurface,
        MaterialTheme.colorScheme.surface
    ),
    onConfirmAction: () -> Unit,
    onCancelAction: () -> Unit
) {
    when (type) {
        DialogType.Exit -> {
            ExitDialog(
                text = text,
                textStyle = textStyle,
                confirmTextButton = confirmTextButton,
                confirmStyle = confirmStyle,
                container = containerColor,
                onConfirmAction = onConfirmAction
            )
        }
        DialogType.Continue -> {
            ContinueDialog(
                text = text,
                textStyle = textStyle,
                confirmTextButton = confirmTextButton,
                confirmStyle = confirmStyle,
                container = containerColor,
                onConfirmAction = onConfirmAction,
                onCancelAction = onCancelAction,
                cancelStyle = cancelStyle,
                cancelButtonText = cancelButtonText
            )
        }
    }
}

@Composable
private fun ExitDialog(
    text: String,
    textStyle: Pair<Color, TextStyle>,
    confirmTextButton: String,
    confirmStyle: Pair<Color, TextStyle>,
    container: Pair<Color, Color>,
    onConfirmAction: () -> Unit,
    modifier: Modifier = Modifier
) {
    AlertDialog(
        onDismissRequest = {},
        confirmButton = {
            Button(onClick = onConfirmAction) {
                Text(
                    text = confirmTextButton,
                    style = confirmStyle.second,
                    color = confirmStyle.first
                )
            }
        },
        shape = RoundedCornerShape(8.dp),
        title = {},
        text = {
            WText(
                text = text,
                style = textStyle.second,
                textColor = textStyle.first
            )
        },
        properties = DialogProperties(dismissOnClickOutside = false),
        textContentColor = container.first,
        containerColor = container.second,
        modifier = modifier
    )
}

@Composable
private fun ContinueDialog(
    onConfirmAction: () -> Unit,
    onCancelAction: () -> Unit,
    container: Pair<Color, Color>,
    text: String,
    confirmTextButton: String,
    cancelButtonText: String,
    textStyle: Pair<Color, TextStyle>,
    confirmStyle: Pair<Color, TextStyle>,
    cancelStyle: Pair<Color, TextStyle>,
    modifier: Modifier = Modifier
) {
    AlertDialog(
        onDismissRequest = {},
        confirmButton = {
            Button(
                onClick = onConfirmAction,
                colors = ButtonDefaults.buttonColors(containerColor = MaterialTheme.colorScheme.secondaryContainer)
            ) {
                Text(
                    text = confirmTextButton,
                    style = confirmStyle.second,
                    color = confirmStyle.first
                )
            }
        },
        dismissButton = {
            Button(
                onClick = onCancelAction,
                colors = ButtonDefaults.buttonColors(containerColor = MaterialTheme.colorScheme.primaryContainer)
            ) {
                Text(
                    text = cancelButtonText,
                    style = cancelStyle.second,
                    color = cancelStyle.first
                )
            }
        },
        shape = RoundedCornerShape(8.dp),
        title = {},
        text = {
            Text(
                text = text,
                style = textStyle.second,
                color = textStyle.first
            )
        },
        properties = DialogProperties(dismissOnClickOutside = false),
        textContentColor = container.first,
        containerColor = container.second,
        modifier = modifier
    )
}