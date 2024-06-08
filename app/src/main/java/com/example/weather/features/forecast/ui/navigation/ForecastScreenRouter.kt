package com.example.weather.features.forecast.ui.navigation

sealed class ForecastScreenRouter(val route: String) {
    data object ForecastRouter : ForecastScreenRouter("forecastRouter")
    data object HomeScreen : ForecastScreenRouter("homeScreen")
    data object HourlyScreenForecast :
        ForecastScreenRouter("hourlyScreenForecast")
}