package com.example.weather.utils.data.db

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.example.weather.features.forecast.data.local.ForecastDao
import com.example.weather.features.forecast.data.local.ForecastTypeConvertor
import com.example.weather.features.forecast.data.local.entites.ForecastEntity

@Database(entities = [ForecastEntity::class], version = 1, exportSchema = false)
@TypeConverters(ForecastTypeConvertor::class)
abstract class WeatherDataBase : RoomDatabase() {
    abstract fun hourlyForecastDao(): ForecastDao
}