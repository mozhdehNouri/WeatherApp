package com.example.weather.features.forecast.di

import com.example.weather.features.forecast.data.ForecastWeatherRepositoryImpl
import com.example.weather.features.forecast.data.remote.ForecastWeatherRemoteDataSource
import com.example.weather.features.forecast.data.remote.ForecastWeatherRemoteDataSourceImpl
import com.example.weather.features.forecast.domain.repository.WeatherForecastRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
abstract class WeatherForecastBindsModule {
    @Binds
    abstract fun bindForecastRemoteDataSource(
        forecastWeatherDataSource:
        ForecastWeatherRemoteDataSourceImpl
    ): ForecastWeatherRemoteDataSource

    @Binds
    abstract fun bindForecastRepository(
        forecastWeatherRepository:
        ForecastWeatherRepositoryImpl
    ): WeatherForecastRepository

}