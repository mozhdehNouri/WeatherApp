package com.example.weather.utils.di

import com.example.weather.utils.di.qualifier.Dispatcher
import com.example.weather.utils.di.qualifier.WeatherDispatchers
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers.Default
import kotlinx.coroutines.Dispatchers.IO

@Module
@InstallIn(SingletonComponent::class)
internal object CoroutineModule {
    @Provides
    @Dispatcher(WeatherDispatchers.IO)
    fun provideIODispatcher(): CoroutineDispatcher = IO

    @Dispatcher(WeatherDispatchers.Default)
    @Provides
    fun provideDefaultDispatcher(): CoroutineDispatcher = Default
}