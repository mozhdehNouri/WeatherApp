package com.example.weather.features.forecast.data.local

import com.example.weather.features.forecast.data.local.entites.ForecastEntity
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class ForecastLocalDataSourceImpl @Inject constructor(
    private val dao:
    ForecastDao
) : ForecastLocalDataSource {
    override suspend fun addHourlyForecast(hourlyForecast: ForecastEntity) =
        dao.addHourlyForecast(hourlyForecast)

    override fun getHourlyForecast(): Flow<ForecastEntity> = dao.getHourlyForecast()
}