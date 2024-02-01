package com.example.weather.utils.data.model

import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.withContext
import retrofit2.Response

sealed class ResultWrapper<out T> {
    data class Success<out T>(val value: T) : ResultWrapper<T>()
    data class GenericError(val code: Int? = null, val errorMessage: String? = null) :
        ResultWrapper<Nothing>()

    data object NetworkError : ResultWrapper<Nothing>()
}

suspend fun <T> apiCallAndCheckResult(
    dispatcher: CoroutineDispatcher,
    apiCall: suspend () -> Response<T>
): ResultWrapper<T> {
    return withContext(dispatcher) {
        try {
            val response = apiCall.invoke()
            checkApiResult(response = response)
        } catch (throwable: Throwable) {
            ResultWrapper.GenericError(errorMessage = throwable.message)
        }
    }
}

fun <T> checkApiResult(
    response: Response<T>,
): ResultWrapper<T> {
    if (response.isSuccessful) {
        val body = response.body()
        if (body != null)
            return ResultWrapper.Success(body)
    }
    // return error response from server
    return ResultWrapper.GenericError(code = response.code(), response.message())
}
