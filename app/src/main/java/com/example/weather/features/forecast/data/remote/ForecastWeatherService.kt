package com.example.weather.features.forecast.data.remote

import com.example.weather.features.forecast.data.remote.dto.ForecastResponseDto
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface ForecastWeatherService {
    @GET("forecast")
    suspend fun getForCastWeather(
        @Query("latitude") latitude: Double,
        @Query("longitude") longitude: Double,
        @Query("hourly") hourly: String = "temperature_2m"
    ): Response<ForecastResponseDto>

}