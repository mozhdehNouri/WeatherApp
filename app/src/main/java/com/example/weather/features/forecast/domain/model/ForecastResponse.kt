package com.example.weather.features.forecast.domain.model

data class HourlyResponse(
    val cloudCover: List<Int>,
    val cloudCoverHigh: List<Int>,
    val cloudCoverLow: List<Int>,
    val cloudCoverMid: List<Int>,
    val rain: List<Double>,
    val showers: List<Double>,
    val snowDepth: List<Double>,
    val snowfall: List<Double>,
    val temperature2m: List<Double>,
    val time: List<String>,
    val visibility: List<Double>,
    val weatherCode: List<Int>
)
