package com.example.weather.features.forecast.ui

import androidx.compose.runtime.Immutable
import com.example.weather.features.forecast.domain.model.HourlyResponse
import com.example.weather.utils.WeatherType
import com.example.weather.utils.WeatherType.Companion.fromWMO

@Immutable
data class ForecastHourlyState(
    val hourlyForecast: ForecastHourlyDetailsState? = null,
    val loading: Boolean = false,
    val errorMessage: String? = null
)

@Immutable
data class ForecastHourlyDetailsState(
    val cloudCover: Int,
    val cloudCoverHigh: Int,
    val cloudCoverLow: Int,
    val cloudCoverMid: Int,
    val rain: Double,
    val showers: Double,
    val snowDepth: Double,
    val snowfall: Double,
    val temperature2m: Double,
    val time: String,
    val visibility: Double,
    val weatherCode: WeatherType
)

fun HourlyResponse.getDetailsByHour(): ForecastHourlyDetailsState? {
    var response: ForecastHourlyDetailsState? = null
    time.forEachIndexed { index, s ->
        response = ForecastHourlyDetailsState(
            cloudCover = cloudCover[index],
            cloudCoverHigh = cloudCoverHigh[index],
            cloudCoverLow = cloudCoverLow[index],
            cloudCoverMid = cloudCoverMid[index],
            rain = rain[index],
            showers = showers[index],
            snowDepth = snowDepth[index],
            snowfall = snowfall[index],
            temperature2m = temperature2m[index],
            time = time.get(index),
            visibility = visibility[index],
            weatherCode = fromWMO(weatherCode[index])
        )
    }
    return response
}






