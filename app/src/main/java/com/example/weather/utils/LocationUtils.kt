package com.example.weather.utils

import android.Manifest
import android.content.Context
import android.content.pm.PackageManager
import android.location.LocationManager
import androidx.activity.ComponentActivity
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import com.google.android.gms.common.api.ResolvableApiException
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.location.LocationServices
import com.google.android.gms.location.LocationSettingsRequest
import com.google.android.gms.location.Priority
import com.google.android.gms.tasks.CancellationTokenSource

fun isLocationEnabled(context: Context): Boolean {
    val locationManager =
        context.getSystemService(ComponentActivity.LOCATION_SERVICE) as LocationManager
    try {
        return locationManager.isProviderEnabled(LocationManager.GPS_PROVIDER)
    } catch (e: Exception) {
        e.printStackTrace()
    }
    return false
}

fun createLocationRequest(context: Context) {
    val locationRequest =
        com.google.android.gms.location.LocationRequest.Builder(
            Priority.PRIORITY_BALANCED_POWER_ACCURACY, 10000
        ).setMinUpdateIntervalMillis(5000).build()
    val builder = LocationSettingsRequest.Builder()
        .addLocationRequest(locationRequest)
    val client = LocationServices.getSettingsClient(context)
    val task = client.checkLocationSettings(builder.build())
    task.addOnSuccessListener {}
    task.addOnFailureListener { e ->
        if (e is ResolvableApiException) {
            try {
                e.startResolutionForResult(
                    context as AppCompatActivity,
                    100
                )
            } catch (e: Exception) {

            }
        }
    }
}

fun provideCurrentLocation(
    context: Context,
    fusedLocationClient: FusedLocationProviderClient,
    requestPermission: () -> Unit
) {
    if (ActivityCompat.checkSelfPermission(
            context,
            Manifest.permission.ACCESS_FINE_LOCATION
        ) == PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(
            context,
            Manifest.permission.ACCESS_COARSE_LOCATION
        ) == PackageManager.PERMISSION_GRANTED
    ) {
        if (isLocationEnabled(context)) {
            val result =
                fusedLocationClient.getCurrentLocation(
                    Priority.PRIORITY_BALANCED_POWER_ACCURACY,
                    CancellationTokenSource().token
                )
            result.addOnCompleteListener {
                val location =
                    "Latitude: " + it.result.latitude + "\n" + "Longitude: " + it.result.longitude
            }
        } else {
            createLocationRequest(context)
        }
    } else {
        requestPermission()
    }
}
