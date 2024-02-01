package com.example.weather.utils.di

import android.content.Context
import androidx.room.Room
import com.example.weather.utils.data.db.WeatherDataBase
import com.example.weather.utils.network.NetworkConnectivityObserver
import com.example.weather.utils.network.NetworkConnectivityObserverImpl
import dagger.Binds
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

//    @Binds
//    fun provideNetwork(conectivity: NetworkConnectivityObserverImpl) : NetworkConnectivityObserver

//    @Provides
//    @Singleton // If you are using SingletonComponent
//    fun provideExampleInterface() : NetworkConnectivityObserver = NetworkConnectivityObserverImpl()


}

@Module
@InstallIn(SingletonComponent::class)
abstract class ProvideInternet {

    @Binds
    abstract fun provideNetwork(conectivity: NetworkConnectivityObserverImpl): NetworkConnectivityObserver

}