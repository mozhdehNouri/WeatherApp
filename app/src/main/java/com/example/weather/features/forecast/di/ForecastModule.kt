package com.example.weather.features.forecast.di

import com.example.weather.features.forecast.data.local.ForecastDao
import com.example.weather.features.forecast.data.remote.ForecastService
import com.example.weather.utils.data.db.WeatherDataBase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit

@Module
@InstallIn(SingletonComponent::class)
object ForecastModule {
    @Provides
    fun provideHourlyForecastService(retrofit: Retrofit): ForecastService {
        return retrofit.create(ForecastService::class.java)
    }

    @Provides
    fun provideHourlyForecastDao(dataBase: WeatherDataBase): ForecastDao {
        return dataBase.hourlyForecastDao()
    }

}