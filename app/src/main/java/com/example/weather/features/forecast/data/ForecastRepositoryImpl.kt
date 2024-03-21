package com.example.weather.features.forecast.data

import android.content.Context
import com.example.weather.features.forecast.data.local.ForecastLocalDataSource
import com.example.weather.features.forecast.data.local.entites.ForecastEntity
import com.example.weather.features.forecast.data.local.entites.asExternalModel
import com.example.weather.features.forecast.data.remote.ForecastRemoteDataSource
import com.example.weather.features.forecast.domain.model.Forecast
import com.example.weather.features.forecast.domain.repository.ForecastRepository
import com.example.weather.features.forecast.domain.utils.AppResult
import com.example.weather.features.forecast.domain.utils.toAppResult
import com.example.weather.utils.network.isInternetAvailable
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class ForecastRepositoryImpl @Inject constructor(
    private val remoteDataSource: ForecastRemoteDataSource,
    private val localDataSource: ForecastLocalDataSource,
    @ApplicationContext private val context: Context
) : ForecastRepository {
    override suspend fun getForecastFromNetwork(
        latitude: Double, longitude: Double
    ): AppResult<Boolean> {
        if (!isInternetAvailable(context)) {
            return AppResult.Error(error = "No Internet connection")
        }
        val result = remoteDataSource.getForecast(
            latitude = latitude, longitude
            = longitude
        ).toAppResult()

        return when (result) {
            is AppResult.Success -> {
                val response = result.data.hourly
                localDataSource.addHourlyForecast(
                    ForecastEntity(
                        id = 0,
                        cloudCover = response.cloudCover,
                        cloudCoverHigh = response.cloudCoverHigh,
                        cloudCoverLow = response.cloudCoverLow,
                        cloudCoverMid = response.cloudCoverMid,
                        rain = response.rain,
                        showers = response.showers,
                        snowDepth = response.snowDepth,
                        snowfall = response.snowfall,
                        temperature2m = response.temperature2m,
                        time = response.time,
                        visibility = response.visibility,
                        weatherCode = response.weatherCode
                    )
                )
                AppResult.Success(true)
            }

            is AppResult.Error -> {
                AppResult.Error(error = result.error)
            }
        }
    }

    override fun getForecastFromDatabase(): Flow<Forecast> = localDataSource.getHourlyForecast()
        .map { it.asExternalModel() }

}