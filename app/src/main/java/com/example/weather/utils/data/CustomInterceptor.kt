package com.example.weather.utils.data

import com.example.weather.utils.di.qualifier.Dispatcher
import com.example.weather.utils.di.qualifier.WeatherDispatchers
import com.example.weather.utils.network.NetworkConnectivityObserver
import com.example.weather.utils.network.NetworkStatus
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch
import okhttp3.Interceptor
import okhttp3.Request
import okhttp3.Response
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class CustomInterceptor @Inject constructor(
    private val connectivityObserver: NetworkConnectivityObserver,
    @Dispatcher(WeatherDispatchers.IO) private val ioDispatcher: CoroutineDispatcher
) : Interceptor {
    private val scope = CoroutineScope(ioDispatcher)
    lateinit var request: Request
    override fun intercept(chain: Interceptor.Chain): Response {
        scope.launch {
            connectivityObserver.observe().collect { networkState ->
                when (networkState) {
                    NetworkStatus.Available -> {
                        request = chain.request()
                    }

                    else -> {
                        throw Exception("No Network Connection")
                    }
                }
            }
        }
//        val request = if (isNetworkReachable()) {
//            chain.request()
//        } else {
//            throw Exception("No Network Connection")
//        }
        return chain.proceed(request)
    }

//    private fun isNetworkReachable(): Boolean {
//        val manager = context.getSystemService(CONNECTIVITY_SERVICE) as
//                ConnectivityManager?
//        val networkInfo = manager!!.activeNetworkInfo
//        return networkInfo != null && networkInfo.isConnected
//    }

}


