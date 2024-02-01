package com.example.weather.utils.data.db

import androidx.room.RoomDatabase
import com.example.weather.features.forecast.data.local.ForecastWeatherDao

//@Database(entities =  , version = 1 , exportSchema = false)
abstract class WeatherDataBase : RoomDatabase() {
    abstract fun forecastWeatherDao(): ForecastWeatherDao
}