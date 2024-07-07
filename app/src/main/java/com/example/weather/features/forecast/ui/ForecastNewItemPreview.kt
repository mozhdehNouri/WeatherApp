package com.example.weather.features.forecast.ui

import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.PreviewParameter
import com.example.weather.ui.theme.WeatherAppTheme
import com.example.weather.utils.ui.DevicePreviews

@DevicePreviews
@Composable
fun ForecastNewPreview(
    @PreviewParameter(ForecastDailyPreviewParameterProvider::class)
    uiState: List<DailyForecastView>
) {
    WeatherAppTheme {
        DailyForeCastScreenBody("", uiState)
    }
}
