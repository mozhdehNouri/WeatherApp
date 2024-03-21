package com.example.weather.features.forecast.domain

import com.example.weather.features.forecast.domain.model.Forecast
import com.example.weather.utils.WeatherType
import java.time.LocalDateTime

fun Forecast.divideForecastByTime(): Map<Int, List<ForecastByTime>> {
    time.mapIndexed { index, s ->
        val cloudCover = cloudCover[index]
        val cloudCoverHigh = cloudCoverHigh[index]
        val cloudCoverLow = cloudCoverLow[index]
        val cloudCoverMid = cloudCoverMid[index]
        val rain = rain[index]
        val showers = showers[index]
        val snowDepth = snowDepth[index]
        val snowfall = snowfall[index]
        val temperature2m = temperature2m[index]
        val time = time[index]
        val visibility = visibility[index]
        val weatherCode = WeatherType.fromWMO(weatherCode[index])


    }


//    val now = LocalDateTime.now()
//    val currentWeatherData = weatherDataMap[0]?.find {
//        val hour = if(now.minute < 30) now.hour else now.hour + 1
//        it.time.hour == hour
//    }

}


data class ForecastByTime(
    val cloudCover: Int,
    val cloudCoverHigh: Int,
    val cloudCoverLow: Int,
    val cloudCoverMid: Int,
    val rain: Double,
    val showers: Double,
    val snowDepth: Double,
    val snowfall: Double,
    val temperature2m: Double,
    val time: LocalDateTime,
    val visibility: Double,
    val weatherCode: WeatherType
)