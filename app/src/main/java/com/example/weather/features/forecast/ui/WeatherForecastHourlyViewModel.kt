package com.example.weather.features.forecast.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.weather.features.forecast.domain.useCase.GetWeatherForecast
import com.example.weather.features.forecast.domain.utils.extractAppResponse
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class WeatherForecastHourlyViewModel @Inject constructor(
    private val getWeatherForecast: GetWeatherForecast
) : ViewModel() {

    private val _uiState = MutableStateFlow(ForecastHourlyState())
    val uiState = _uiState.asStateFlow()
    fun getForecastHourly(
        latitude: Double,
        longitude: Double
    ) = viewModelScope.launch {
        _uiState.update {
            it.copy(loading = true)
        }
        val result =
            getWeatherForecast(latitude, longitude).extractAppResponse()
        _uiState.update {
            it.copy(
                loading = false,
                hourlyForecast = result.first?.getDetailsByHour(),
                errorMessage =
                result.second
            )
        }
    }
}