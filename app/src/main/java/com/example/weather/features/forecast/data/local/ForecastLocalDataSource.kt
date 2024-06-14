package com.example.weather.features.forecast.data.local

import androidx.datastore.preferences.core.Preferences
import com.example.weather.features.forecast.data.local.entites.DailyForecastEntity
import com.example.weather.features.forecast.data.local.entites.LocationInfo
import kotlinx.coroutines.flow.Flow

interface ForecastLocalDataSource {
    suspend fun addDailyForecast(
        dailyForecast: List<DailyForecastEntity>,
        locationInfo: LocationInfo
    )
    fun getDailyForecast(): Flow<List<DailyForecastEntity>>
    fun isFirstTimeRead(): Flow<Preferences>
    fun isFirstTimeWrite(): Flow<Boolean>

}