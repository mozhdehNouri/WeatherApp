package com.example.weather.features.forecast.domain.utils

import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.withContext
import retrofit2.Response

sealed class ApiResultWrapper<out T> {
    data class Success<out T>(val data: T) : ApiResultWrapper<T>()
    data class Error(val errorMessage: String? = null) :
        ApiResultWrapper<Nothing>()
}

suspend fun <T> apiCallAndCheckResult(
    dispatcher: CoroutineDispatcher,
    apiCall: suspend () -> Response<T>
): ApiResultWrapper<T> {
    return withContext(dispatcher) {
        try {
            val response = apiCall.invoke()
            checkApiResult(response = response)
        } catch (throwable: Throwable) {
            ApiResultWrapper.Error(errorMessage = throwable.message)
        }
    }
}

fun <T> checkApiResult(
    response: Response<T>,
): ApiResultWrapper<T> {
    if (response.isSuccessful) {
        val body = response.body()
        if (body != null)
            return ApiResultWrapper.Success(body)
    }
    // return error response from server
    return ApiResultWrapper.Error(response.code().toString())
}

fun <T> ApiResultWrapper<T>.toAppResult() =
    when (this) {
        is ApiResultWrapper.Success -> AppResult.Success(data)
        is ApiResultWrapper.Error -> AppResult.Error(error = errorMessage.toString())
    }


