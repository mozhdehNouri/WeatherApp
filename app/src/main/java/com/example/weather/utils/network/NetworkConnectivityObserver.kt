package com.example.weather.utils.network

import kotlinx.coroutines.flow.Flow

interface NetworkConnectivityObserver {
    fun observe(): Flow<NetworkStatus>
}

enum class NetworkStatus {
    Available, Unavailable, Losing, Lost
}