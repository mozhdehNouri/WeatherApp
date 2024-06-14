package com.example.weather.features.forecast.ui

import android.content.Context
import android.content.ContextWrapper
import androidx.activity.ComponentActivity
import androidx.annotation.DrawableRes
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.sizeIn
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.compose.LifecycleEventEffect
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.example.todoappwithcleanarchitecture.R
import com.example.weather.MainActivity
import com.example.weather.ui.components.DialogType
import com.example.weather.ui.components.LocalLazyRowContentPadding
import com.example.weather.ui.components.LocalTextPadding
import com.example.weather.ui.components.WColumn_Parent
import com.example.weather.ui.components.WDialog
import com.example.weather.ui.components.WImage
import com.example.weather.ui.components.WLoading_Text
import com.example.weather.ui.components.WText
import com.example.weather.utils.component.rememberMutableDialogState
import com.example.weather.utils.location.checkLocationAccess
import com.example.weather.utils.location.rememberLocationRequestLauncher
import kotlin.system.exitProcess

@Composable
fun DailyForecastRouter() {
    DailyForecastScreen()
}

@Composable
private fun DailyForecastScreen(viewModel: ForecastViewModel = hiltViewModel()) {
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()
    val context = LocalContext.current.applicationContext
    var locationPermission by remember { mutableStateOf(context.checkLocationAccess()) }
    val dialogState = rememberMutableDialogState(initialData = "")

    val locationLauncher = rememberLocationRequestLauncher { result ->
        if (result) viewModel.getForecast()
        else dialogState.showDialog("You must provide location permission")
        locationPermission = result
    }

    LifecycleEventEffect(event = Lifecycle.Event.ON_RESUME) {
        locationPermission = context.checkLocationAccess()
    }

    LaunchedEffect(locationPermission) {
        if (locationPermission) {
            viewModel.getForecast()
        } else {
            locationLauncher.requestLocationAccess()
        }
    }
    if (dialogState.isVisible.value) {
        WDialog(type = DialogType.Continue, text = dialogState.dialogData.value, onCancelAction = {
            val activity = MainActivity()
            activity.finish()
            exitProcess(0)
        }, onConfirmAction = {
            locationLauncher.requestLocationAccess()
            dialogState.hideDialog()
        }, confirmTextButton = "Continue", cancelButtonText = "Exit")
    }

    when (uiState) {
        is DailyForecastUiState.Loading -> {
            LoadingBody()
        }

        is DailyForecastUiState.Success -> {
            DailyForeCastScreenBody((uiState as DailyForecastUiState.Success).allForecast)
        }

        is DailyForecastUiState.Error -> {
            dialogState.showDialog((uiState as DailyForecastUiState.Error).message)
        }

        is DailyForecastUiState.LocationError -> {
            dialogState.showDialog((uiState as DailyForecastUiState.LocationError).message)
        }
    }
}

@Composable
fun DailyForeCastScreenBody(
    uiState: List<DailyForecastView>
) {
    WColumn_Parent(
        header = {
            Header(
                cityName = "Tehran",
                currentTime = "11:20"
            )
        },
        content = {
            Middle(
                currentForecast = uiState.findCurrentForecast(),
                modifier = Modifier.weight(1f)
            )
        },
        bottom = { Bottom(uiState) }
    )
}

@Composable
private fun Header(
    cityName: String,
    currentTime: String,
    modifier: Modifier = Modifier
) {
    Row(
        modifier = modifier
            .fillMaxWidth()
            .statusBarsPadding(),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        WText(
            text = cityName,
            textColor = MaterialTheme.colorScheme.onBackground,
            style = MaterialTheme.typography.titleMedium
        )
        WText(
            text = currentTime,
            textColor = MaterialTheme.colorScheme.onBackground.copy(alpha = 0.6f),
            style = MaterialTheme.typography.titleSmall
        )
    }
}

@Composable
private fun Middle(
    currentForecast: DailyForecastView,
    modifier: Modifier = Modifier
) {
    val textPadding = LocalTextPadding.current
    Column(
        modifier.fillMaxWidth(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Spacer(modifier = Modifier.padding(top = 28.dp))
        WImage(
            imageId = currentForecast.weatherType.iconRes,
            modifier = Modifier.sizeIn(
                minWidth = 100.dp,
                minHeight = 100.dp
            )
        )
        Spacer(modifier = Modifier.padding(top = 28.dp))
        WText(
            text = currentForecast.weatherType.weatherDesc,
            textColor = MaterialTheme.colorScheme.onBackground,
            style = MaterialTheme.typography.headlineSmall,
            modifier = Modifier.padding(textPadding)
        )
        WText(
            text = currentForecast.temperature2m.toString(),
            textColor = MaterialTheme.colorScheme.onBackground,
            style = MaterialTheme.typography.titleMedium,
            modifier = Modifier.padding(textPadding)
        )
        Spacer(modifier = Modifier.padding(top = 28.dp))
        WImage(
            R.drawable.temperature,
            modifier = Modifier.fillMaxWidth(),
            contentScale = ContentScale.Fit
        )
    }

}

@Composable
private fun Bottom(list: List<DailyForecastView>) {
    DailyForecastList(
        list, modifier = Modifier.navigationBarsPadding()
    )
}

@Composable
private fun DailyForecastList(
    list: List<DailyForecastView>,
    modifier: Modifier = Modifier
) {
    val contentPadding = LocalLazyRowContentPadding.current
    LazyRow(
        modifier = modifier,
        horizontalArrangement = Arrangement.spacedBy(contentPadding)
    ) {
        items(items = list) { forecast ->
            DailyForecastListItem(
                imageId = forecast.weatherType.iconRes,
                temperature = forecast.temperature2m.toString(),
                time = forecast.time
            )
        }
    }
}

@Composable
private fun DailyForecastListItem(
    modifier: Modifier = Modifier,
    @DrawableRes imageId: Int,
    temperature: String,
    time: String
) {
    val textPadding = LocalTextPadding.current
    Column(
        modifier = modifier
            .clip(RoundedCornerShape(8.dp)),
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

@Composable
fun LoadingBody(modifier: Modifier = Modifier) {
    Box(modifier.fillMaxWidth(), contentAlignment = Alignment.Center) {
        WLoading_Text(
            text = "Loading data Please Wait...",
            textColor = MaterialTheme.colorScheme.onBackground,
            style = MaterialTheme.typography.titleMedium
        )
    }
}

fun Context.findActivity(): ComponentActivity? = when (this) {
    is ComponentActivity -> this
    is ContextWrapper -> baseContext.findActivity()
    else -> null
}