package com.example.weather.features.forecast.ui

import androidx.compose.ui.tooling.preview.PreviewParameterProvider
import com.example.weather.features.forecast.ui.ForecastDailyPreviewDataParameter.dailyForecastView
import com.example.weather.utils.WeatherType

/**
 * This [PreviewParameterProvider](https://developer.android.com/reference/kotlin/androidx/compose/ui/tooling/preview/PreviewParameterProvider)
 * provides list of [DailyForecastView] for Composable previews.
 */
class ForecastDailyPreviewParameterProvider :
    PreviewParameterProvider<DailyForecastUiState> {

    override val values: Sequence<DailyForecastUiState.Success> =
        sequenceOf(DailyForecastUiState.Success(dailyForecastView, ""))
}

object ForecastDailyPreviewDataParameter {

    val dailyForecastView = listOf(
        DailyForecastView(
            cloudCover = 0,
            cloudCoverHigh = 0,
            cloudCoverLow = 0,
            cloudCoverMid = 0,
            rain = 0.0,
            showers = 0.0,
            snowDepth = 0.0,
            snowfall = 0.0,
            temperature2m = 0.0,
            time = "11:22",
            date = "2022/3/14",
            visibility = 0.0,
            weatherType = WeatherType.DenseDrizzle
        ),
        DailyForecastView(
            cloudCover = 0,
            cloudCoverHigh = 0,
            cloudCoverLow = 0,
            cloudCoverMid = 0,
            rain = 0.0,
            showers = 0.0,
            snowDepth = 0.0,
            snowfall = 0.0,
            temperature2m = 0.0,
            time = "11:22",
            date = "2022/3/14",
            visibility = 0.0,
            weatherType = WeatherType.DenseDrizzle
        ),
        DailyForecastView(
            cloudCover = 0,
            cloudCoverHigh = 0,
            cloudCoverLow = 0,
            cloudCoverMid = 0,
            rain = 0.0,
            showers = 0.0,
            snowDepth = 0.0,
            snowfall = 0.0,
            temperature2m = 0.0,
            time = "11:22",
            date = "2022/3/14",
            visibility = 0.0,
            weatherType = WeatherType.DenseDrizzle
        ),
        DailyForecastView(
            cloudCover = 0,
            cloudCoverHigh = 0,
            cloudCoverLow = 0,
            cloudCoverMid = 0,
            rain = 1.0,
            showers = 0.0,
            snowDepth = 0.0,
            snowfall = 1.0,
            temperature2m = 0.0,
            time = "11:22",
            date = "2022/3/14",
            visibility = 0.0,
            weatherType = WeatherType.DenseDrizzle
        )
    )
}