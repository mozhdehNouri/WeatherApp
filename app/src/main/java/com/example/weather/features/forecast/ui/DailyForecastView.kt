package com.example.weather.features.forecast.ui

import androidx.compose.runtime.Immutable
import com.example.weather.utils.WeatherType

@Immutable
data class DailyForecastView(
    val cloudCover: Int = 0,
    val cloudCoverHigh: Int = 0,
    val cloudCoverLow: Int = 0,
    val cloudCoverMid: Int = 0,
    val rain: Double = 0.0,
    val showers: Double = 0.0,
    val snowDepth: Double = 0.0,
    val snowfall: Double = 0.0,
    val temperature2m: Double = 0.0,
    val time: String = "",
    val date: String = "",
    val visibility: Double = 0.0,
    val weatherType: WeatherType = WeatherType.ClearSky
)