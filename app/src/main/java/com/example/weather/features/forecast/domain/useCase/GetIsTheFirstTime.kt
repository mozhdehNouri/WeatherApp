package com.example.weather.features.forecast.domain.useCase

import androidx.datastore.preferences.core.Preferences
import com.example.weather.features.forecast.domain.repository.ForecastRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetIsTheFirstTime @Inject constructor(private val repository: ForecastRepository) {
    operator fun invoke(): Flow<Preferences> =
        repository.isFirstTimeRead()
}