package com.example.weather.features.forecast.data

import android.content.Context
import androidx.datastore.preferences.core.Preferences
import com.example.weather.features.forecast.data.local.ForecastLocalDataSource
import com.example.weather.features.forecast.data.local.entites.DailyForecastEntity
import com.example.weather.features.forecast.data.local.entites.mapper.toExternalModel
import com.example.weather.features.forecast.data.remote.ForecastRemoteDataSource
import com.example.weather.features.forecast.domain.model.DailyForecast
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
    ): AppResult<Unit> {
        if (!isInternetAvailable(context)) {
            return AppResult.Error(error = "No Internet connection")
        }
        val result = remoteDataSource.getForecast(
            latitude = latitude, longitude = longitude
        ).toAppResult()

        return when (result) {
            is AppResult.Success -> {
                val response = result.data.hourly
                val dailyForecastList = buildList {
                    response.time.forEachIndexed { index, time ->
                        this.add(
                            DailyForecastEntity(
                                index = index,
                                cloudCover = response.cloudCover[index],
                                cloudCoverHigh = response.cloudCoverHigh[index],
                                cloudCoverLow = response.cloudCoverLow[index],
                                cloudCoverMid = response.cloudCoverMid[index],
                                rain = response.rain[index],
                                showers = response.showers[index],
                                snowDepth = response.snowDepth[index],
                                snowfall = response.snowfall[index],
                                temperature2m = response.temperature2m[index],
                                time,
                                visibility = response.visibility[index],
                                weatherCode = response.weatherCode[index]

                            )
                        )
                    }
                }
                localDataSource.addDailyForecast(
                    dailyForecast = dailyForecastList,
                    lastUpdate = System.currentTimeMillis()
                )
                AppResult.Success(Unit)
            }

            is AppResult.Error -> {
                AppResult.Error(error = result.error)
            }
        }
    }

    override fun getDailyForecastFromDatabase(): Flow<List<DailyForecast>> =
        localDataSource.getDailyForecast().map { it.toExternalModel() }

    override fun getLastUpdate(): Flow<Long> =
        localDataSource.getLastUpdate()


    override fun isFirstTimeRead(): Flow<Preferences> =
        localDataSource.isFirstTimeRead()

    override fun isFirstTimeWrite(): Flow<Boolean> =
        localDataSource.isFirstTimeWrite()
}