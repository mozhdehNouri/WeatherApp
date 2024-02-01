package com.example.weather

import android.app.Application
import dagger.hilt.android.HiltAndroidApp

/**
 *The Application class in Android is the base class within
 * an Android app that contains all other components such as activities and services.
 * The Application class, or any subclass of the Application class,
 * is instantiated before any other class when the process for your application/package is created.
 */
@HiltAndroidApp
class WeatherApplication : Application() {


}