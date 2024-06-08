package com.example.weather.features.forecast.di

import com.example.weather.features.forecast.data.ForecastRepositoryImpl
import com.example.weather.features.forecast.data.local.ForecastLocalDataSource
import com.example.weather.features.forecast.data.local.ForecastLocalDataSourceImpl
import com.example.weather.features.forecast.data.remote.ForecastRemoteDataSource
import com.example.weather.features.forecast.data.remote.ForecastRemoteDataSourceImpl
import com.example.weather.features.forecast.domain.repository.ForecastRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class ForecastBindsModule {
    @Binds
    @Singleton
    abstract fun bindForecastRemoteDataSource(
        forecastDataSource: ForecastRemoteDataSourceImpl
    ): ForecastRemoteDataSource

    @Binds
    @Singleton
    abstract fun bindForecastRepository(
        forecastRepository: ForecastRepositoryImpl
    ): ForecastRepository

    @Binds
    @Singleton
    abstract fun bindForecastLocalDataSource(impl: ForecastLocalDataSourceImpl):
            ForecastLocalDataSource
}