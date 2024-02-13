package com.example.weather.features.forecast.domain.repository

import com.example.weather.features.forecast.domain.model.HourlyResponse
import com.example.weather.features.forecast.domain.utils.AppResult

interface WeatherForecastRepository {
    suspend fun getWeatherForecast(
        latitude: Double,
        longitude: Double
    ): AppResult<HourlyResponse?>

}