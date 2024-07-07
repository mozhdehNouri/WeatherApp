package com.example.weather.features.forecast.domain.useCase

import com.example.weather.features.forecast.domain.repository.ForecastRepository
import com.example.weather.features.forecast.domain.utils.AppResult
import javax.inject.Inject

class SendLocationUseCase @Inject constructor(
    private val repository: ForecastRepository
) {
    suspend operator fun invoke(
        latitude: Double,
        longitude: Double
    ): AppResult<Unit> {
        return repository.getForecastFromNetwork(
            latitude = latitude,
            longitude = longitude
        )
    }
}