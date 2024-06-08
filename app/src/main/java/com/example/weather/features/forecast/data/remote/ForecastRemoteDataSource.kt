package com.example.weather.features.forecast.data.remote

import com.example.weather.features.forecast.data.remote.dto.ForecastResponseDto
import com.example.weather.features.forecast.domain.utils.ApiResultWrapper

interface ForecastRemoteDataSource {
    suspend fun getForecast(
        latitude: Double,
        longitude: Double
    ): ApiResultWrapper<ForecastResponseDto>

}