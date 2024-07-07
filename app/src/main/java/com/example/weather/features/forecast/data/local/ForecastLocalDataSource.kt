package com.example.weather.features.forecast.data.local

import androidx.datastore.preferences.core.Preferences
import com.example.weather.features.forecast.data.local.entites.DailyForecastEntity
import kotlinx.coroutines.flow.Flow

interface ForecastLocalDataSource {
    suspend fun addDailyForecast(
        dailyForecast: List<DailyForecastEntity>, lastUpdate: Long
    )
    fun getDailyForecast(): Flow<List<DailyForecastEntity>>
    fun getLastUpdate(): Flow<Long>
    fun isFirstTimeRead(): Flow<Preferences>
    fun isFirstTimeWrite(): Flow<Boolean>

}