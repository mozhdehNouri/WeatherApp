package com.example.weather.features.forecast.domain.model


data class DailyForecast(
    val cloudCover: Int,
    val cloudCoverHigh: Int,
    val cloudCoverLow: Int,
    val cloudCoverMid: Int,
    val rain: Double,
    val showers: Double,
    val snowDepth: Double,
    val snowfall: Double,
    val temperature2m: Double,
    val time: String,
    val visibility: Double,
    val weatherCode: Int
)