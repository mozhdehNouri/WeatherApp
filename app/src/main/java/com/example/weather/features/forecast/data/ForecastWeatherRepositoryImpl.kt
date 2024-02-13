package com.example.weather.features.forecast.data

import android.content.Context
import com.example.weather.features.forecast.data.remote.ForecastWeatherRemoteDataSource
import com.example.weather.features.forecast.domain.model.HourlyResponse
import com.example.weather.features.forecast.domain.repository.WeatherForecastRepository
import com.example.weather.features.forecast.domain.utils.AppResult
import com.example.weather.features.forecast.domain.utils.toAppResult
import com.example.weather.utils.network.isInternetAvailable
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Inject

class ForecastWeatherRepositoryImpl @Inject constructor(
    private val forecastRemoteDataSource: ForecastWeatherRemoteDataSource,
    @ApplicationContext val context: Context
) : WeatherForecastRepository {
    override suspend fun getWeatherForecast(
        latitude: Double, longitude: Double
    ): AppResult<HourlyResponse> {
        if (!isInternetAvailable(context)) {
            return AppResult.Error(error = "No Internet connection")
        }
        val result = forecastRemoteDataSource.getWeatherForecast(
            latitude = latitude, longitude
            = longitude
        ).toAppResult()

        return when (result) {
            is AppResult.Success -> {
                AppResult.Success(result.data.hourly.toHourlyDomainResponse())
            }

            is AppResult.Error -> {
                AppResult.Error(error = result.error)
            }
        }
    }
}