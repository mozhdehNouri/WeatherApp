package com.example.weather.features.forecast.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.weather.features.forecast.domain.useCase.GetForecastUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.mapNotNull
import kotlinx.coroutines.flow.stateIn
import javax.inject.Inject

@HiltViewModel
class ForecastViewModel @Inject constructor(
    private val getForecast: GetForecastUseCase
) : ViewModel() {

    val uiState: StateFlow<ForecastView> = getForecast().mapNotNull { list ->
        list.toExternalModel()
    }.stateIn(
        scope = viewModelScope,
        started = SharingStarted.WhileSubscribed(5_000),
        initialValue = ForecastView()
    )
}


//public fun <T> emptyFlow(): Flow<T> = EmptyFlow
//
//private object EmptyFlow : Flow<Nothing> {
//    override suspend fun collect(collector: FlowCollector<Nothing>) = Unit
//}