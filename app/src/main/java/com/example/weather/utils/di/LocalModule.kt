package com.example.weather.utils.di

import android.content.Context
import androidx.room.Room
import com.example.weather.utils.data.db.WeatherDataBase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
internal object LocalModule {
    @Provides
    @Singleton
    fun providesUserDatabase(@ApplicationContext context: Context): WeatherDataBase =
        Room.databaseBuilder(context, WeatherDataBase::class.java, "WeatherDataBase").build()
}