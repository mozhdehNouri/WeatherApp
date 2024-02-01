package com.example.weather.features.forecast.data.di

import com.example.weather.features.forecast.data.local.ForecastWeatherDao
import com.example.weather.features.forecast.data.remote.ForecastWeatherService
import com.example.weather.utils.data.db.WeatherDataBase
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class ForecastWeatherModule {

    @Singleton
    fun provideForecastService(retrofit: Retrofit): ForecastWeatherService {
        return retrofit.create(ForecastWeatherService::class.java)
    }

    @Singleton
    fun provideForeCastDao(db: WeatherDataBase): ForecastWeatherDao {
        return db.forecastWeatherDao()
    }

}