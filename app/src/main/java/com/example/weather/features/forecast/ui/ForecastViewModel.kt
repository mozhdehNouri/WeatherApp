package com.example.weather.features.forecast.ui

import android.Manifest
import android.content.Context
import android.content.pm.PackageManager
import android.location.Location
import androidx.annotation.RequiresPermission
import androidx.core.app.ActivityCompat
import androidx.datastore.preferences.core.booleanPreferencesKey
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.weather.features.forecast.domain.model.DailyForecast
import com.example.weather.features.forecast.domain.useCase.GetForecastUseCase
import com.example.weather.features.forecast.domain.useCase.GetIsTheFirstTime
import com.example.weather.features.forecast.domain.useCase.SendLocationUseCase
import com.example.weather.features.forecast.domain.useCase.SendUserIsFirstTime
import com.example.weather.features.forecast.domain.utils.AppResult
import com.example.weather.features.forecast.ui.mapper.asExternalModel
import com.google.android.gms.location.LocationServices
import com.google.android.gms.location.Priority
import com.google.android.gms.tasks.CancellationTokenSource
import dagger.hilt.android.lifecycle.HiltViewModel
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import kotlinx.coroutines.tasks.await
import java.time.LocalTime
import javax.inject.Inject

@HiltViewModel
class ForecastViewModel @Inject constructor(
    private val getDailyForecast: GetForecastUseCase,
    private val readFirstTime: GetIsTheFirstTime,
    private val writeIsTheFirstTime: SendUserIsFirstTime,
    private val locationUseCase: SendLocationUseCase,
    @ApplicationContext private val context: Context
) : ViewModel() {

    init {
        readLocationFirstTimeStatus()
    }

    private val _uiState = MutableStateFlow<DailyForecastUiState>(DailyForecastUiState.Loading)
    val uiState = _uiState.asStateFlow()
    var isFirstTime: MutableStateFlow<Boolean> = MutableStateFlow(true)
        private set

    private val locationClient =
        LocationServices.getFusedLocationProviderClient(context.applicationContext)

    private fun readLocationFirstTimeStatus() = viewModelScope.launch {
        readFirstTime().collect { prf ->
            isFirstTime.update {
                prf.contains(booleanPreferencesKey("FirstTime"))
            }
        }
    }

    fun writeLocationFirstTimeStatus() = writeIsTheFirstTime()

    fun getForecast() = viewModelScope.launch {
        if (ActivityCompat.checkSelfPermission(
                context,
                Manifest.permission.ACCESS_FINE_LOCATION
            ) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(
                context,
                Manifest.permission.ACCESS_COARSE_LOCATION
            ) != PackageManager.PERMISSION_GRANTED
        ) {
            _uiState.update {
                DailyForecastUiState.LocationError()
            }
        }
        getCurrentLocation()?.let { loc ->
            _uiState.update { DailyForecastUiState.Loading }
            when (val response = locationUseCase(loc.latitude, loc.longitude)) {
                is AppResult.Success -> {
                    _uiState.update {
                        val list =
                            getDailyForecast().map(List<DailyForecast>::asExternalModel).first()
                        DailyForecastUiState.Success(list)
                    }
                }
                is AppResult.Error -> {
                    _uiState.update {
                        DailyForecastUiState.Error(response.error)
                    }
                }
            }
        } ?: run {
            _uiState.update {
                DailyForecastUiState.LocationError()
            }
        }
    }

    @RequiresPermission(anyOf = [Manifest.permission.ACCESS_COARSE_LOCATION, Manifest.permission.ACCESS_FINE_LOCATION])
    private suspend fun getCurrentLocation(): Location? {
        var locationInfo: Location? = null

        when (PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.checkSelfPermission(
                context,
                Manifest.permission.ACCESS_FINE_LOCATION
            ) -> {
                locationInfo = locationClient.getCurrentLocation(
                    Priority.PRIORITY_HIGH_ACCURACY,
                    CancellationTokenSource().token,
                ).await()
            }

            ActivityCompat.checkSelfPermission(
                context,
                Manifest.permission.ACCESS_COARSE_LOCATION
            ) -> {
                locationInfo = locationClient.getCurrentLocation(
                    Priority.PRIORITY_BALANCED_POWER_ACCURACY,
                    CancellationTokenSource().token,
                ).await()
            }

            else -> {
                PackageManager.PERMISSION_DENIED
            }
        }
        return locationInfo
    }
}

sealed interface DailyForecastUiState {
    data object Loading : DailyForecastUiState
    data class Success(val allForecast: List<DailyForecastView>) : DailyForecastUiState

    data class Error(val message: String) : DailyForecastUiState
    data class LocationError(val message: String = "Can not retrieve location") :
        DailyForecastUiState
}

fun String.extractTime(): LocalTime {
    val localDateTime = LocalTime.parse(this)
    return localDateTime
}

fun List<DailyForecastView>.findCurrentForecast(): DailyForecastView {
    return this.find {
        it.time.extractTime().hour == LocalTime.now().hour
    } ?: DailyForecastView()
}