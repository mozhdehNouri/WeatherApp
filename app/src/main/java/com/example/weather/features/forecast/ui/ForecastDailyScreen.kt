package com.example.weather.features.forecast.ui

import androidx.annotation.DrawableRes
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.example.todoappwithcleanarchitecture.R
import com.example.weather.ui.components.LocalLazyRowContentPadding
import com.example.weather.ui.components.LocalTextPadding
import com.example.weather.ui.components.WColumn_Parent
import com.example.weather.ui.components.WImage
import com.example.weather.ui.components.WText
import com.example.weather.ui.theme.Black

@Composable
fun DailyForecastRouter() {
    DailyForecastScreen()
}

@Composable
private fun DailyForecastScreen(viewModel: ForecastViewModel = hiltViewModel()) {
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()

    DailyForeCastScreenBody(uiState)
}

@Composable
fun DailyForeCastScreenBody(uiState: DailyForecastUiState) {
    WColumn_Parent(
        header = { Header(cityName = "Tehran", currentTime = "11:20") },
        content = {
            Middle(
                weatherStatusImage = uiState.currentForecast.weatherType.iconRes,
                weatherStatus = uiState.currentForecast.weatherType.weatherDesc,
                temperature = uiState.currentForecast.temperature2m.toString()
            )
        },
        bottom = { Bottom(uiState.allForecast) }
    )
}

@Composable
fun Header(
    cityName: String,
    currentTime: String,
    modifier: Modifier = Modifier
) {
    Row(
        modifier = modifier.fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        WText(
            text = cityName, textColor = Black,
        )
        WText(
            text = currentTime, textColor = Black
        )
    }
}

@Composable
fun Middle(
    weatherStatusImage: Int,
    weatherStatus: String,
    temperature: String,
    modifier: Modifier = Modifier
) {
    Column(
        modifier.fillMaxWidth(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        WImage(imageId = weatherStatusImage)
        WText(text = weatherStatus, textColor = Black)
        WText(text = temperature, textColor = Black)
        WImage(R.drawable.temperature, modifier = Modifier.fillMaxWidth())

    }

}

@Composable
fun Bottom(list: List<DailyForecastView>) {
    DailyForecastList(
        list
    )
}

@Composable
fun DailyForecastList(
    list: List<DailyForecastView>,
    modifier: Modifier = Modifier
) {
    val contentPadding = LocalLazyRowContentPadding.current
    LazyRow(
        modifier = modifier,
        horizontalArrangement = Arrangement.spacedBy(contentPadding)
    ) {
        items(items = list) {
            DailyForecastListItem(
                imageId = it.weatherType.iconRes,
                temperature = it.temperature2m.toString(),
                time = it.time
            )
        }
    }
}


@Composable
fun DailyForecastListItem(
    modifier: Modifier = Modifier,
    @DrawableRes imageId: Int,
    temperature: String,
    time: String
) {
    val textPadding = LocalTextPadding.current
    Column(
        modifier = modifier
            .clip(RoundedCornerShape(20.dp))
            .background(Color.Gray.copy(alpha = 0.3f)),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = time,
            style = MaterialTheme.typography.labelLarge,
            modifier = Modifier
                .padding(textPadding)
        )
        Image(
            painter = painterResource(id = imageId),
            contentDescription = null,
            modifier =
            Modifier.size(64.dp),
            contentScale = ContentScale.Fit
        )
        Text(
            text = temperature,
            style = MaterialTheme.typography.labelMedium,
            modifier = Modifier
                .padding(textPadding)
        )
    }
}


