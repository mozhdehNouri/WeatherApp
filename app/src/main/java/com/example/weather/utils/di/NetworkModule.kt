package com.example.weather.utils.di

import android.content.Context
import android.util.Config.DEBUG
import com.example.weather.utils.data.CustomInterceptor
import com.squareup.moshi.Moshi
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import okhttp3.Cache
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import timber.log.Timber
import java.io.File
import java.util.concurrent.TimeUnit
import javax.inject.Singleton

private const val CLIENT_CACHE_SIZE = 2 * 10 * 1024 * 1024L // 20 MiB
private const val CLIENT_CACHE_DIRECTORY = "https-json-cache"
private const val TIMEOUT = 30L


@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {
    @Singleton
    @Provides
    fun providesRetrofit(
        converterFactory: MoshiConverterFactory,
        client: OkHttpClient,
        baseUrl: String,
    ): Retrofit {
        return Retrofit.Builder()
            .addConverterFactory(converterFactory)
            .client(client)
            .baseUrl(baseUrl)
            .build()
    }

    @Singleton
    @Provides
    fun providesMoshiConverterFactory(moshi: Moshi): MoshiConverterFactory {
        return MoshiConverterFactory
            .create(moshi)
    }

    @Singleton
    @Provides
    fun providesOkHttpClient(
        interceptor: HttpLoggingInterceptor,
        cache: Cache,
        customInterceptor: CustomInterceptor
    ): OkHttpClient {
        return OkHttpClient.Builder()
            .addInterceptor(interceptor)
            .followRedirects(true)
            .connectTimeout(TIMEOUT, TimeUnit.SECONDS)
            .writeTimeout(TIMEOUT, TimeUnit.SECONDS)
            .addInterceptor(customInterceptor)
            .readTimeout(TIMEOUT, TimeUnit.SECONDS)
            .followSslRedirects(true)
            .retryOnConnectionFailure(true)
            .cache(cache)
            .build()
    }

    @Singleton
    @Provides
    fun providesHttpLoggingInterceptor(): HttpLoggingInterceptor {
        return HttpLoggingInterceptor { message ->
            Timber.i(message)
        }.apply {
            level =
                if (DEBUG) HttpLoggingInterceptor.Level.BODY else HttpLoggingInterceptor.Level.NONE
        }
    }

    @Singleton
    @Provides
    fun providesCache(@ApplicationContext context: Context): Cache {
        return Cache(
            directory = File(context.cacheDir, CLIENT_CACHE_DIRECTORY),
            maxSize = CLIENT_CACHE_SIZE,
        )
    }

}