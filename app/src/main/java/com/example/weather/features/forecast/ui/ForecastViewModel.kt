package com.example.weather.features.forecast.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.weather.features.forecast.domain.useCase.GetForecastUseCase
import com.example.weather.features.forecast.ui.mapper.asExternalModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import java.time.LocalTime
import javax.inject.Inject


@HiltViewModel
class ForecastViewModel @Inject constructor(
    private val getForecast: GetForecastUseCase
) : ViewModel() {

    val uiState: StateFlow<DailyForecastUiState> =
        getForecast().map { it.asExternalModel() }
            .map(::DailyForecastUiState)
            .stateIn(
                scope = viewModelScope,
                started = SharingStarted.WhileSubscribed(5_000),
                initialValue = DailyForecastUiState(emptyList())
            )
}

data class DailyForecastUiState(
    val allForecast: List<DailyForecastView>
) {

    val currentForecast: DailyForecastView = allForecast.find {
        it.time.extractTime().hour == LocalTime.now().hour
    } ?: DailyForecastView()
}

fun String.extractTime(): LocalTime {
    val localDateTime = LocalTime.parse(this)
    return localDateTime
}