package com.example.weather.features.forecast.domain.utils

sealed class AppResult<out T> {
    data class Success<out T>(val data: T) : AppResult<T>()
    data class Error<out T>(val error: String) : AppResult<T>()

    override fun toString(): String {
        return when (this) {
            is Success -> "Success[data=$data]"
            is Error -> "Error[error=$error]"
        }
    }
}

fun <T> AppResult<T>.extractAppResponse(): Pair<T?, String?> =
    when (this) {
        is AppResult.Success -> Pair(data, null)
        is AppResult.Error -> Pair(null, error)
    }