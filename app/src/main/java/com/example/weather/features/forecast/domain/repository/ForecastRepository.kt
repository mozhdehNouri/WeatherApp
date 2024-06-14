package com.example.weather.features.forecast.domain.repository

import androidx.datastore.preferences.core.Preferences
import com.example.weather.features.forecast.domain.model.DailyForecast
import com.example.weather.features.forecast.domain.utils.AppResult
import kotlinx.coroutines.flow.Flow

interface ForecastRepository {
    suspend fun getForecastFromNetwork(
        latitude: Double,
        longitude: Double
    ): AppResult<Boolean>

    fun getDailyForecastFromDatabase(): Flow<List<DailyForecast>>

    fun isFirstTimeRead(): Flow<Preferences>
    fun isFirstTimeWrite(): Flow<Boolean>
}