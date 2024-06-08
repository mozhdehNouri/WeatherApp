package com.example.weather.features.forecast.data.local

import com.example.weather.features.forecast.data.local.entites.DailyForecastEntity
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class ForecastLocalDataSourceImpl @Inject constructor(
    private val dao: ForecastDao
) : ForecastLocalDataSource {

    override suspend fun addDailyForecast(dailyForecast: List<DailyForecastEntity>) =
        dao.addDailyForecast(dailyForecast)

    override fun getDailyForecast(): Flow<List<DailyForecastEntity>> =
        dao.getDailyForecast()
}