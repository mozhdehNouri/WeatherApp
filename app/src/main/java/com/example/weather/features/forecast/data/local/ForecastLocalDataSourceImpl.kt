package com.example.weather.features.forecast.data.local

import android.content.Context
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.booleanPreferencesKey
import androidx.datastore.preferences.core.edit
import com.example.weather.features.forecast.data.local.entites.DailyForecastEntity
import com.example.weather.features.forecast.data.local.entites.LocationInfo
import com.example.weather.utils.di.weatherDataStore
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class ForecastLocalDataSourceImpl @Inject constructor(
    private val dao: ForecastDao,
    @ApplicationContext private val context: Context
) : ForecastLocalDataSource {

    override suspend fun addDailyForecast(
        dailyForecast: List<DailyForecastEntity>,
        locationInfo: LocationInfo
    ) = dao.addDailyForecast(dailyForecast, locationInfo)

    override fun getDailyForecast(): Flow<List<DailyForecastEntity>> =
        dao.getDailyForecast()

    override fun isFirstTimeRead(): Flow<Preferences> =
        context.weatherDataStore.data

    override fun isFirstTimeWrite(): Flow<Boolean> {
        return flow {
            context.weatherDataStore.edit { store ->
                store[booleanPreferencesKey("FirstTime")] = false
                emit(true)
            }
        }
    }
}