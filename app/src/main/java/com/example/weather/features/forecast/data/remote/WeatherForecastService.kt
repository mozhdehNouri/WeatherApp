package com.example.weather.features.forecast.data.remote

import com.example.weather.features.forecast.data.remote.dto.ForecastResponseDto
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query


interface WeatherForecastService {
    @GET("forecast")
    suspend fun getHourlyForCastWeather(
        @Query("latitude") latitude: Double,
        @Query("longitude") longitude: Double,
        @Query("hourly") hourly: String = Hourly_Key,
        @Query("forecast_days") forecastDays: String = ForecastDays
    ): Response<ForecastResponseDto>

    companion object {
        private const val Hourly_Key =
            "temperature_2m,rain,showers,snowfall,snow_depth,weather_code,cloud_cover,cloud_cover_low,cloud_cover_mid,cloud_cover_high,visibility"
        private const val ForecastDays = "1"
    }
}