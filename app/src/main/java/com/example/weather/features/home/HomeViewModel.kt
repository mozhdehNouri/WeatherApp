package com.example.weather.features.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.weather.features.forecast.domain.useCase.SendLocationUseCase
import com.example.weather.features.forecast.domain.utils.AppResult
import com.example.weather.utils.di.qualifier.Dispatcher
import com.example.weather.utils.di.qualifier.WeatherDispatchers
import com.example.weather.utils.location.LocationTracker
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val locationUseCase: SendLocationUseCase,
    private val locationTracker: LocationTracker,
    @Dispatcher(WeatherDispatchers.IO) private val ioDispatcher: CoroutineDispatcher
) : ViewModel() {

    private val _uiState =
        MutableStateFlow<HomeViewState>(HomeViewState.Loading)
    val uiState = _uiState.asStateFlow()


    fun sendLocation() =
        viewModelScope.launch(ioDispatcher) {
            locationTracker.getCurrentLocation()?.let { location ->
            when (val result = locationUseCase.invoke(
                location.latitude,
                location.longitude
            )) {
                is AppResult.Success -> {
                    _uiState.update {
                        HomeViewState.Success
                    }
                }

                is AppResult.Error -> {
                    _uiState.update {
                        HomeViewState.Error(message = result.error)
                    }
                }
            }
            } ?: run {
                _uiState.update {
                    HomeViewState.LocationError("cannot retrieve location")
                }
            }
        }
    }


sealed interface HomeViewState {
    data object Loading : HomeViewState
    data object Success : HomeViewState
    data class Error(val message: String) : HomeViewState
    data class LocationError(val message: String) : HomeViewState

}