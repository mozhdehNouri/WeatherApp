package com.example.weather.features.forecast.ui.navigation

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import com.example.weather.features.forecast.ui.DailyForecastRouter
import com.example.weather.features.home.HomeRouter

fun NavGraphBuilder.forecastNavGraph(navController: NavController) {
    navigation(
        startDestination = ForecastScreenRouter.HomeScreen.route, route =
        ForecastScreenRouter.ForecastRouter.route
    ) {
        composable(route = ForecastScreenRouter.HomeScreen.route) {
            HomeRouter(onNavigateToHourlyForecast = {
                navController.navigate(ForecastScreenRouter.HourlyScreenForecast.route)
            })
        }
        composable(ForecastScreenRouter.HourlyScreenForecast.route) {
            DailyForecastRouter()
        }
    }
}