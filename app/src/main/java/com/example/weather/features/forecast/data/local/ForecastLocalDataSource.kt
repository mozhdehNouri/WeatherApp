package com.example.weather.features.forecast.data.local

import com.example.weather.features.forecast.data.local.entites.ForecastEntity
import kotlinx.coroutines.flow.Flow

interface ForecastLocalDataSource {
    suspend fun addHourlyForecast(hourlyForecast: ForecastEntity)
    fun getHourlyForecast(): Flow<ForecastEntity>

}