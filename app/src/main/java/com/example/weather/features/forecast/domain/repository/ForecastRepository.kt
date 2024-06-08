package com.example.weather.features.forecast.domain.repository

import com.example.weather.features.forecast.domain.model.DailyForecast
import com.example.weather.features.forecast.domain.utils.AppResult
import kotlinx.coroutines.flow.Flow

interface ForecastRepository {
    suspend fun getForecastFromNetwork(
        latitude: Double,
        longitude: Double
    ): AppResult<Boolean>

    fun getDailyForecastFromDatabase(): Flow<List<DailyForecast>>
}