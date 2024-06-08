package com.example.weather.features.home

import android.content.pm.PackageManager
import android.widget.Toast
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.core.content.ContextCompat
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.example.todoappwithcleanarchitecture.BuildConfig
import com.example.todoappwithcleanarchitecture.R
import com.example.weather.ui.theme.clearSky_dark_onSecondaryContainer
import com.example.weather.utils.component.weathericon.AnimatableSun
import com.example.weather.utils.ui.DevicePreviews

@Composable
fun HomeRouter(
    onNavigateToHourlyForecast: () -> Unit
) {
    HomeScreen(
        onNavigateToHourlyForecast = onNavigateToHourlyForecast,
    )
}

@Composable
private fun HomeScreen(
    viewModel: HomeViewModel = hiltViewModel(),
    onNavigateToHourlyForecast: () -> Unit
) {
    val context = LocalContext.current
    val homeViewState by viewModel.uiState.collectAsStateWithLifecycle()

    val locationPermissions = arrayOf(
        android.Manifest.permission.ACCESS_FINE_LOCATION,
        android.Manifest.permission.ACCESS_COARSE_LOCATION
    )
    val locationPermissionRequest =
        rememberLauncherForActivityResult(ActivityResultContracts.RequestMultiplePermissions()) { permissions ->
            val areGranted =
                permissions.values.reduce { acc, next -> acc && next }
            if (areGranted) {
                viewModel.sendLocation()
            } else {
                Toast.makeText(
                    context,
                    "Permission denies",
                    Toast.LENGTH_SHORT
                ).show()
            }
        }

    LaunchedEffect(key1 = Unit) {
        if (locationPermissions.all {
                ContextCompat.checkSelfPermission(
                    context,
                    it
                ) == PackageManager.PERMISSION_GRANTED
            }) {
            viewModel.sendLocation()
        } else {
            locationPermissionRequest.launch(locationPermissions)
        }
    }

    when (homeViewState) {
        is HomeViewState.Loading -> {
            Toast.makeText(context, "loading", Toast.LENGTH_SHORT).show()
        }

        is HomeViewState.Success -> {
            onNavigateToHourlyForecast()
        }

        is HomeViewState.Error -> {
            Toast.makeText(
                context,
                (homeViewState as HomeViewState.Error).message,
                Toast.LENGTH_SHORT
            ).show()
        }

        is HomeViewState.LocationError -> {
            Toast.makeText(
                context,
                (homeViewState as HomeViewState.LocationError).message,
                Toast.LENGTH_SHORT
            ).show()
        }
    }
    HomeBody()
}

@Composable
private fun HomeBody(
    modifier: Modifier = Modifier
) {
    Box(
        modifier = modifier
            .fillMaxSize()
            .background(clearSky_dark_onSecondaryContainer)
    ) {
        AnimatableSun(
            Modifier
                .size(150.dp)
                .padding(top = 100.dp)
                .align(Alignment.TopCenter)
        )
        Text(
            text = stringResource(id = R.string.welcome),
            style = MaterialTheme.typography.headlineLarge,
            modifier = Modifier.align(Alignment.Center)
        )

        Text(
            text = BuildConfig.VERSION_CODE.toString(),
            style = MaterialTheme.typography.labelSmall,
            modifier = Modifier.align(Alignment.BottomCenter)
        )
    }
}

@DevicePreviews
@Composable
private fun HomeScreenPreview() {
    HomeBody()
}