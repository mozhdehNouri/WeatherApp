package com.example.weather.features.forecast.data.local.entites.mapper

import com.example.weather.features.forecast.data.local.entites.DailyForecastEntity
import com.example.weather.features.forecast.domain.model.DailyForecast

private fun DailyForecastEntity.asExternalModel() = DailyForecast(
    cloudCover = cloudCover,
    cloudCoverHigh = cloudCoverHigh,
    cloudCoverLow = cloudCoverLow,
    cloudCoverMid = cloudCoverMid,
    rain = rain,
    showers = showers,
    snowDepth = snowDepth,
    snowfall = snowfall,
    temperature2m = temperature2m,
    time = time,
    visibility = visibility,
    weatherCode = weatherCode
)

fun List<DailyForecastEntity>.toExternalModel(): List<DailyForecast> =
    map {
        it.asExternalModel()
    }