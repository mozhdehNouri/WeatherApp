package com.example.weather.features.forecast.data.local.entites

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.weather.features.forecast.domain.model.Forecast

@Entity
data class ForecastEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Int,
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

fun ForecastEntity.asExternalModel() = Forecast(
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