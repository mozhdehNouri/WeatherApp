package com.example.weather.features.forecast.domain.useCase

import com.example.weather.features.forecast.domain.model.Forecast
import com.example.weather.features.forecast.domain.repository.ForecastRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject


class GetForecastUseCase @Inject constructor(private val repository: ForecastRepository) {
    operator fun invoke(): Flow<Forecast> {
        return repository.getForecastFromDatabase()
    }
}