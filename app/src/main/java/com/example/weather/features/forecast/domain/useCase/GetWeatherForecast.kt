package com.example.weather.features.forecast.domain.useCase

import com.example.weather.features.forecast.domain.model.HourlyResponse
import com.example.weather.features.forecast.domain.repository.WeatherForecastRepository
import com.example.weather.features.forecast.domain.utils.AppResult
import javax.inject.Inject

class GetWeatherForecast @Inject constructor(
    private val forecastRepository: WeatherForecastRepository
) {
    suspend operator fun invoke(
        latitude: Double,
        longitude: Double
    ): AppResult<HourlyResponse?> {
        return forecastRepository.getWeatherForecast(
            latitude = latitude,
            longitude = longitude
        )
    }
}