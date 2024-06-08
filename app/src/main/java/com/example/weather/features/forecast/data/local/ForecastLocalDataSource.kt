package com.example.weather.features.forecast.data.local

import com.example.weather.features.forecast.data.local.entites.DailyForecastEntity
import kotlinx.coroutines.flow.Flow

interface ForecastLocalDataSource {
    suspend fun addDailyForecast(dailyForecast: List<DailyForecastEntity>)
    fun getDailyForecast(): Flow<List<DailyForecastEntity>>

}