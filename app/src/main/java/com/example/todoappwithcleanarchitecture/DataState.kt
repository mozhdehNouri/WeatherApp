package com.example.todoappwithcleanarchitecture

sealed class DataState<T> {

    data class Loading<T>(val loading: Boolean) : DataState<T>()
    data class Success<T>(val data: T) : DataState<T>()
    data class Error<T>(val errorShowingType: ErrorShowingType) :
        DataState<T>()


}

sealed class ErrorShowingType {
    data class Toast(val message: String) : ErrorShowingType()
    data class SnackBar(val message: String) : ErrorShowingType()

}


