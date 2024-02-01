package com.example.weather.features.forecast.data.remote.dto


import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class ForecastResponseDto(
    @Json(name = "elevation")
    val elevation: Double?,
    @Json(name = "generationtime_ms")
    val generationtimeMs: Double?,
    @Json(name = "hourly")
    val hourly: Hourly?,
    @Json(name = "hourly_units")
    val hourlyUnits: HourlyUnits?,
    @Json(name = "latitude")
    val latitude: Double?,
    @Json(name = "longitude")
    val longitude: Double?,
    @Json(name = "timezone")
    val timezone: String?,
    @Json(name = "timezone_abbreviation")
    val timezoneAbbreviation: String?,
    @Json(name = "utc_offset_seconds")
    val utcOffsetSeconds: Int?
) {
    @JsonClass(generateAdapter = true)
    data class Hourly(
        @Json(name = "temperature_2m")
        val temperature2m: List<Double?>?,
        @Json(name = "time")
        val time: List<String?>?
    )

    @JsonClass(generateAdapter = true)
    data class HourlyUnits(
        @Json(name = "temperature_2m")
        val temperature2m: String?,
        @Json(name = "time")
        val time: String?
    )
}