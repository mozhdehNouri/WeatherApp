package com.example.weather.features.forecast.data.local

import androidx.room.Dao
import androidx.room.Query
import androidx.room.Upsert
import com.example.weather.features.forecast.data.local.entites.DailyForecastEntity
import com.example.weather.features.forecast.data.local.entites.LastTimeUpdate
import kotlinx.coroutines.flow.Flow

@Dao
interface ForecastDao {

    @Upsert
    suspend fun upsertDailyForecast(
        hourlyForecast: List<DailyForecastEntity>,
        lastUpdate: LastTimeUpdate
    )

    @Query("select * from DailyForecastEntity")
    fun getDailyForecast(): Flow<List<DailyForecastEntity>>

    @Query("select lastTime from LastTimeUpdate")
    fun lastTimeUpdate(): Flow<Long>

}