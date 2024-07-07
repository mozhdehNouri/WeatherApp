package com.example.weather.features.forecast.data.local.entites

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class DailyForecastEntity internal constructor(
    @PrimaryKey(autoGenerate = false)
    val index: Int,
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

@Entity
data class LastTimeUpdate(
    @PrimaryKey
    val lastTime: Long
)