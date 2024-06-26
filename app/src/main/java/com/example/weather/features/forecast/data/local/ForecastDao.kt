package com.example.weather.features.forecast.data.local

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.weather.features.forecast.data.local.entites.DailyForecastEntity
import com.example.weather.features.forecast.data.local.entites.LocationInfo
import kotlinx.coroutines.flow.Flow

@Dao
interface ForecastDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun addDailyForecast(
        hourlyForecast: List<DailyForecastEntity>,
        locationInfo: LocationInfo
    )

    @Query("select * from DailyForecastEntity")
    fun getDailyForecast(): Flow<List<DailyForecastEntity>>

}