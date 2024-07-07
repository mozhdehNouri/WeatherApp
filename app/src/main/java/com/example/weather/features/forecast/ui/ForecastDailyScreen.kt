package com.example.weather.features.forecast.ui

import androidx.annotation.DrawableRes
import androidx.compose.animation.AnimatedContent
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.togetherWith
import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.padding
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
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.compose.LifecycleEventEffect
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.example.todoappwithcleanarchitecture.R
import com.example.weather.MainActivity
import com.example.weather.features.forecast.ui.utils.convertToTemperature
import com.example.weather.ui.components.DialogType
import com.example.weather.ui.components.LocalLazyRowContentPadding
import com.example.weather.ui.components.LocalRowPadding
import com.example.weather.ui.components.LocalTextPadding
import com.example.weather.ui.components.WColumn_Parent
import com.example.weather.ui.components.WDialog
import com.example.weather.ui.components.WImage
import com.example.weather.ui.components.WLoading_Text
import com.example.weather.ui.components.WText
import com.example.weather.ui.components.convertVectorToBitmap
import com.example.weather.ui.components.generateDominantColorState
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
    val isFirstTimeSendRequest by viewModel.isFirstTime.collectAsStateWithLifecycle()
    var locationPermission by remember { mutableStateOf(context.checkLocationAccess()) }
    val dialogState = rememberMutableDialogState(initialData = "")
    var dialogType by remember {
        mutableStateOf<ForecastDialogType>(ForecastDialogType.RequestError)
    }
    val locationLauncher = rememberLocationRequestLauncher { result ->
        if (result) {
            viewModel.sendLocationRequest()
            if (isFirstTimeSendRequest) viewModel.writeLocationFirstTimeStatus()
        } else {
            dialogType =
                ForecastDialogType.LocationError
            dialogState.showDialog(context.getString(R.string.location_error_message))
        }
        locationPermission = result
    }
    LifecycleEventEffect(event = Lifecycle.Event.ON_RESUME) {
        locationPermission = context.checkLocationAccess()
        if (isFirstTimeSendRequest) {
            locationLauncher.requestLocationAccess()
        } else if (locationPermission) {
            viewModel.sendLocationRequest()
        }
    }
    LaunchedEffect(key1 = uiState) {
        if (!isFirstTimeSendRequest) {
            viewModel.getDailyForecast()
        }
    }

    when (dialogType) {
        ForecastDialogType.RequestError -> {
            ForeCastDialog(dialogState.isVisible.value,
                text = dialogState.dialogData.value,
                onCancelAction = {
                    dialogState.hideDialog()
                },
                onSubmitAction = {
                    viewModel.sendLocationRequest()
                    dialogState.hideDialog()
                }
            )
        }

        ForecastDialogType.LocationError -> {
            ForeCastDialog(dialogState.isVisible.value,
                text = dialogState.dialogData.value,
                onCancelAction = {
                    if (!isFirstTimeSendRequest) {
                        dialogState.hideDialog()
                    }
                    val activity = MainActivity()
                    activity.finish()
                    exitProcess(0)
                },
                onSubmitAction = {
                    locationLauncher.requestLocationAccess()
                    dialogState.hideDialog()
                }
            )
        }
    }
    AnimatedContent(
        uiState,
        transitionSpec = {
            fadeIn(animationSpec = tween(3000)) togetherWith fadeOut(
                animationSpec = tween(1000)
            )
        },
        label = "Animated Content"
    ) { targetState ->
        if (!dialogState.isVisible.value) {
            when (targetState) {
                is DailyForecastUiState.Loading -> {
                    LoadingBody()
                }

                is DailyForecastUiState.Success -> {
                    DailyForeCastScreenBody(
                        weatherList = targetState.allForecast,
                        updateTime = targetState.lastUpdate
                    )
                }

                is DailyForecastUiState.Error -> {
                    dialogType = ForecastDialogType.RequestError
                    dialogState.showDialog(targetState.message)
                }

                is DailyForecastUiState.LocationError -> {
                    dialogType = ForecastDialogType.LocationError
                    dialogState.showDialog(targetState.message)
                }
            }
        }

    }
}

@Composable
fun DailyForeCastScreenBody(
    updateTime: String,
    weatherList: List<DailyForecastView>
) {
    val context = LocalContext.current
    val image = convertVectorToBitmap(
        context,
        weatherList.findCurrentForecast().weatherType.iconRes
    )
    val swatch =
        remember(weatherList.findCurrentForecast().weatherType.iconRes) { image!!.generateDominantColorState() }
    val dominantColors = listOf(
        Color(swatch.rgb).copy(alpha = 0.2f),
        MaterialTheme.colorScheme.primaryContainer
    )
    val dominantGradient = remember { dominantColors }

    WColumn_Parent(
        header = {
            Header(
                currentTime = updateTime
            )
        },
        content = {
            Middle(
                currentForecast = weatherList.findCurrentForecast(),
                modifier = Modifier.weight(1f)
            )
        },
        bottom = { Bottom(weatherList) },
        backgroundColor = dominantGradient
    )
}

@Composable
private fun Header(
    currentTime: String,
    modifier: Modifier = Modifier
) {
    val rowPadding = LocalRowPadding.current
    Row(
        modifier = modifier
            .fillMaxWidth()
            .padding(rowPadding)
            .statusBarsPadding(),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        WText(
            text = stringResource(id = R.string.last_update_label),
            textColor = MaterialTheme.colorScheme.onTertiaryContainer,
            style = MaterialTheme.typography.titleMedium
        )
        WText(
            text = currentTime,
            textColor = MaterialTheme.colorScheme.onTertiaryContainer.copy(
                alpha = 0.6f
            ),
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
        modifier
            .fillMaxSize()
            .border(
                2.dp,
                color = MaterialTheme.colorScheme.onBackground,
                shape = RoundedCornerShape(8.dp)
            ),
        verticalArrangement = Arrangement.SpaceAround,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Spacer(modifier = Modifier.padding(top = 28.dp))
        WImage(
            imageId = currentForecast.weatherType.iconRes,
            modifier = Modifier.fillMaxWidth(0.7f)
        )
        Spacer(modifier = Modifier.padding(top = 28.dp))
        WText(
            text = currentForecast.weatherType.weatherDesc,
            textColor = MaterialTheme.colorScheme.onBackground.copy(alpha = 0.85f),
            style = MaterialTheme.typography.titleLarge,
            modifier = Modifier.padding(textPadding)
        )
        WText(
            text = currentForecast.temperature2m.toString()
                .convertToTemperature(),
            textColor = MaterialTheme.colorScheme.onBackground,
            style = MaterialTheme.typography.headlineLarge,
            modifier = Modifier.padding(textPadding)
        )
        Spacer(modifier = Modifier.weight(1f))
        WImage(
            R.drawable.temperature,
            modifier = Modifier.fillMaxWidth(),
            contentScale = ContentScale.Inside
        )
    }

}

@Composable
private fun Bottom(list: List<DailyForecastView>) {
    Column {
        WText(
            text = stringResource(id = R.string.last_24_hour_label),
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 8.dp, bottom = 4.dp),
            style = MaterialTheme.typography.titleMedium,
            textColor = MaterialTheme.colorScheme.onSurface
        )
        DailyForecastList(list)
    }
}

@Composable
private fun DailyForecastList(
    list: List<DailyForecastView>,
    modifier: Modifier = Modifier
) {
    val contentPadding = LocalLazyRowContentPadding.current
    LazyRow(
        modifier = modifier
            .navigationBarsPadding(),
        horizontalArrangement = Arrangement.spacedBy(contentPadding)
    ) {
        items(items = list) { forecast ->
            DailyForecastListItem(
                imageId = forecast.weatherType.iconRes,
                temperature = forecast.temperature2m.toString()
                    .convertToTemperature(),
                time = forecast.time
            )
        }
    }
}

@Composable
private fun DailyForecastListItem(
    @DrawableRes imageId: Int,
    temperature: String,
    time: String,
    modifier: Modifier = Modifier
) {
    val textPadding = LocalTextPadding.current
    val listItemPadding = LocalLazyRowContentPadding.current
    Column(
        modifier = modifier
            .border(
                1.dp,
                color = MaterialTheme.colorScheme.onBackground.copy(alpha = 0.5f),
                shape = RoundedCornerShape(8.dp)
            )
            .padding(listItemPadding),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = time,
            style = MaterialTheme.typography.labelLarge,
            color = MaterialTheme.colorScheme.onBackground.copy(alpha = 0.6f),
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
            color = MaterialTheme.colorScheme.onBackground.copy(alpha = 0.6f),
            modifier = Modifier
                .padding(textPadding)
        )
    }
}

@Composable
private fun LoadingBody(modifier: Modifier = Modifier) {
    Box(modifier.fillMaxWidth(), contentAlignment = Alignment.Center) {
        WLoading_Text(
            text = stringResource(id = R.string.loading_text),
            textColor = MaterialTheme.colorScheme.onBackground,
            style = MaterialTheme.typography.titleMedium
        )
    }
}

@Composable
fun ForeCastDialog(
    dialogState: Boolean,
    text: String,
    onCancelAction: () -> Unit,
    onSubmitAction: () -> Unit
) {
    if (dialogState) {
        WDialog(
            type = DialogType.Continue,
            text = text,
            onCancelAction = onCancelAction,
            onConfirmAction = onSubmitAction,
            confirmTextButton = stringResource(id = R.string.continue_label),
            cancelButtonText = stringResource(id = R.string.dismiss_label)
        )
    }
}

enum class ForecastDialogType {
    LocationError,
    RequestError
}