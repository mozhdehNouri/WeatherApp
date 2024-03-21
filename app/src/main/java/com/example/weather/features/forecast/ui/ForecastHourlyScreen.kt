package com.example.weather.features.forecast.ui

import androidx.annotation.DrawableRes
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.example.weather.utils.ui.DevicePreviews

@Composable
fun ForecastHourlyRouter(
    viewModel: ForecastViewModel = hiltViewModel()
) {
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()

    val context = LocalContext.current
    LazyColumn {
        item(uiState.cloudCover) {
            Text(text = uiState.cloudCover.toString())
        }
    }

//    ForecastHourlyScreen()

}

@Composable
private fun ForecastHourlyScreen() {

}

//@Composable
//private fun ForecastHourlyBody(
//    modifier: Modifier = Modifier
//) {
//    Column(modifier = modifier.fillMaxSize()) {
//        WeatherStatus()
//    }
//
//}

@Composable
fun ColumnScope.WeatherStatus(
    @DrawableRes imageId: Int,
    weatherDescription: String
) {
    Image(painter = painterResource(id = imageId), contentDescription = null)
    Text(text = weatherDescription)
}

@DevicePreviews
@Composable
fun ForecastHourlyScreenPreview() {
//    ForecastHourlyBody()
}

