package com.example.weather.features.forecast.data.remote.dto


import com.example.weather.features.forecast.domain.model.HourlyResponse
import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class ForecastResponseDto(
    @Json(name = "hourly")
    val hourly: HourlyResponseDto
)

@JsonClass(generateAdapter = true)
data class HourlyResponseDto(
    @Json(name = "cloud_cover")
    val cloudCover: List<Int>,
    @Json(name = "cloud_cover_high")
    val cloudCoverHigh: List<Int>,
    @Json(name = "cloud_cover_low")
    val cloudCoverLow: List<Int>,
    @Json(name = "cloud_cover_mid")
    val cloudCoverMid: List<Int>,
    val rain: List<Double>,
    val showers: List<Double>,
    @Json(name = "snow_depth")
    val snowDepth: List<Double>,
    val snowfall: List<Double>,
    @Json(name = "temperature_2m")
    val temperature2m: List<Double>,
    val time: List<String>,
    val visibility: List<Double>,
    @Json(name = "weather_code")
    val weatherCode: List<Int>
) {
    fun toHourlyDomainResponse() = HourlyResponse(
        cloudCover = cloudCover,
        cloudCoverHigh = cloudCoverHigh,
        cloudCoverLow = cloudCoverLow,
        cloudCoverMid = cloudCoverMid,
        rain = rain,
        showers = showers,
        snowDepth = snowDepth,
        snowfall = snowfall,
        temperature2m = temperature2m,
        time = time,
        visibility = visibility,
        weatherCode = weatherCode
    )
}