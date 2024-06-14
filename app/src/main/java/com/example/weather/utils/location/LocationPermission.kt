package com.example.weather.utils.location

import android.Manifest
import android.content.Context
import android.content.pm.PackageManager
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.runtime.Composable
import androidx.core.content.ContextCompat

fun Context.checkPermissionGranted(permission: String): Boolean =
    ContextCompat.checkSelfPermission(
        this,
        permission
    ) == PackageManager.PERMISSION_GRANTED


fun Context.checkLocationAccess(): Boolean =
    checkPermissionGranted(Manifest.permission.ACCESS_FINE_LOCATION) ||
        checkPermissionGranted(Manifest.permission.ACCESS_COARSE_LOCATION)

@JvmInline
value class LocationAccessRequestLauncher(private val launcher: ActivityResultLauncher<Array<String>>) {
    fun requestLocationAccess() {
        launcher.launch(
            arrayOf(
                Manifest.permission.ACCESS_COARSE_LOCATION,
                Manifest.permission.ACCESS_FINE_LOCATION
            )
        )
    }
}

@Composable
inline fun rememberLocationRequestLauncher(crossinline onResult: (Boolean) -> Unit):
        LocationAccessRequestLauncher {
    val launcher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.RequestMultiplePermissions(),
        onResult = { grantResults ->
            val locationAccess =
                grantResults[Manifest.permission.ACCESS_COARSE_LOCATION] == true ||
                        grantResults[Manifest.permission.ACCESS_FINE_LOCATION] == true
            onResult(locationAccess)
        },
    )
    return LocationAccessRequestLauncher(launcher)
}


