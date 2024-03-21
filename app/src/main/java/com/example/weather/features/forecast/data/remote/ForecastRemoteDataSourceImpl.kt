package com.example.weather.features.forecast.data.remote

import com.example.weather.features.forecast.data.remote.dto.ForecastResponseDto
import com.example.weather.features.forecast.domain.utils.ApiResultWrapper
import com.example.weather.features.forecast.domain.utils.apiCallAndCheckResult
import com.example.weather.utils.di.qualifier.Dispatcher
import com.example.weather.utils.di.qualifier.WeatherDispatchers
import kotlinx.coroutines.CoroutineDispatcher
import javax.inject.Inject

class ForecastRemoteDataSourceImpl @Inject constructor(
    @Dispatcher(WeatherDispatchers.IO) private val ioDispatcher: CoroutineDispatcher,
    private val service: ForecastService
) : ForecastRemoteDataSource {
    override suspend fun getForecast(
        latitude: Double,
        longitude: Double
    ): ApiResultWrapper<ForecastResponseDto> =
        apiCallAndCheckResult(dispatcher = ioDispatcher, apiCall = {
            service.getHourlyForCast(
                latitude = latitude,
                longitude = longitude
            )
        })
}

