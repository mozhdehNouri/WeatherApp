package com.example.weather.utils.data.db

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.weather.features.forecast.data.local.ForecastDao
import com.example.weather.features.forecast.data.local.entites.DailyForecastEntity
import com.example.weather.features.forecast.data.local.entites.LastTimeUpdate

@Database(
    entities = [DailyForecastEntity::class, LastTimeUpdate::class],
    version = 1,
    exportSchema = false
)
abstract class WeatherDataBase : RoomDatabase() {
    abstract fun hourlyForecastDao(): ForecastDao
}