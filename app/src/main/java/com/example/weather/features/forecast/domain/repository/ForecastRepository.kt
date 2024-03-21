package com.example.weather.features.forecast.domain.repository

import com.example.weather.features.forecast.domain.model.Forecast
import com.example.weather.features.forecast.domain.utils.AppResult
import kotlinx.coroutines.flow.Flow

interface ForecastRepository {
    suspend fun getForecastFromNetwork(
        latitude: Double,
        longitude: Double
    ): AppResult<Boolean>

    fun getForecastFromDatabase(): Flow<Forecast>

}