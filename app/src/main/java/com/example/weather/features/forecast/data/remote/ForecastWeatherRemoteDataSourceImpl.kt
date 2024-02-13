package com.example.weather.features.forecast.data.remote

import com.example.weather.features.forecast.data.remote.dto.ForecastResponseDto
import com.example.weather.features.forecast.domain.utils.ApiResultWrapper
import com.example.weather.features.forecast.domain.utils.apiCallAndCheckResult
import com.example.weather.utils.di.qualifier.Dispatcher
import com.example.weather.utils.di.qualifier.WeatherDispatchers
import kotlinx.coroutines.CoroutineDispatcher
import javax.inject.Inject

class ForecastWeatherRemoteDataSourceImpl @Inject constructor(
    @Dispatcher(WeatherDispatchers.IO) private val ioDispatcher: CoroutineDispatcher,
    private val service: WeatherForecastService
) : ForecastWeatherRemoteDataSource {
    override suspend fun getWeatherForecast(
        latitude: Double,
        longitude: Double
    ): ApiResultWrapper<ForecastResponseDto> =
        apiCallAndCheckResult(dispatcher = ioDispatcher, apiCall = {
            service.getHourlyForCastWeather(
                latitude = latitude,
                longitude = longitude
            )
        })
}

