package com.example.weather.utils.di

import com.example.weather.utils.location.DefaultLocationTracker
import com.example.weather.utils.location.LocationTracker
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import kotlinx.coroutines.ExperimentalCoroutinesApi
import javax.inject.Singleton

@ExperimentalCoroutinesApi
@Module
@InstallIn(SingletonComponent::class)
abstract class LocationModule {
    @Binds
    @Singleton
    abstract fun bindLocation(locationProvider: DefaultLocationTracker): LocationTracker


}