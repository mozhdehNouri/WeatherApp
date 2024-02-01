package com.example.weather.utils.di.qualifier

import javax.inject.Qualifier

@Qualifier
@Retention(AnnotationRetention.RUNTIME)
annotation class Dispatcher(val dispatcher: WeatherDispatchers)

enum class WeatherDispatchers {
    Default,
    IO
}
