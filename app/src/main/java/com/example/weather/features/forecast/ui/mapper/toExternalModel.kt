package com.example.weather.features.forecast.ui.mapper

import com.example.weather.features.forecast.domain.model.DailyForecast
import com.example.weather.features.forecast.ui.DailyForecastView
import com.example.weather.utils.WeatherType

fun DailyForecast.toExternalModel() = DailyForecastView(
    cloudCover = cloudCover,
    cloudCoverHigh = cloudCoverHigh,
    cloudCoverLow = cloudCoverLow,
    cloudCoverMid = cloudCoverMid,
    rain = rain,
    showers = showers,
    snowDepth = snowDepth,
    snowfall = snowfall,
    temperature2m = temperature2m,
    time = time.substringAfter("T"),
    date = time.substringBefore("T"),
    visibility = visibility,
    weatherType = WeatherType.fromWMO(weatherCode)
)

fun List<DailyForecast>.asExternalModel(): List<DailyForecastView> = map {
    it.toExternalModel()
}
