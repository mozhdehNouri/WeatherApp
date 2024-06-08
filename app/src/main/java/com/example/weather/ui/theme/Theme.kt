package com.example.weather.ui.theme

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable

private val ClearSkyLightColorPalette = lightColorScheme(
    primary = clearSky_light_primary,
    onPrimary = clearSky_light_onPrimary,
    primaryContainer = clearSky_light_primaryContainer,
    onPrimaryContainer = clearSky_light_onPrimaryContainer,
    secondary = clearSky_light_secondary,
    onSecondary = clearSky_light_onSecondary,
    secondaryContainer = clearSky_light_secondaryContainer,
    onSecondaryContainer = clearSky_light_onSecondaryContainer,
    tertiary = clearSky_light_tertiary,
    onTertiary = clearSky_light_onTertiary,
    tertiaryContainer = clearSky_light_tertiaryContainer,
    onTertiaryContainer = clearSky_light_onTertiaryContainer,
    error = clearSky_light_error,
    errorContainer = clearSky_light_errorContainer,
    onError = clearSky_light_onError,
    onErrorContainer = clearSky_light_onErrorContainer,
    background = clearSky_light_background,
    onBackground = clearSky_light_onBackground,
    surface = clearSky_light_surface,
    onSurface = clearSky_light_onSurface,
)
//private val ClearSkyDarkColors = darkColorScheme(
//    primary = clearSky_dark_primary,
//    onPrimary = clearSky_dark_onPrimary,
//    primaryContainer = clearSky_dark_primaryContainer,
//    onPrimaryContainer = clearSky_dark_onPrimaryContainer,
//    secondary = clearSky_dark_secondary,
//    onSecondary = clearSky_dark_onSecondary,
//    secondaryContainer = clearSky_dark_secondaryContainer,
//    onSecondaryContainer = clearSky_dark_onSecondaryContainer,
//    tertiary = clearSky_dark_tertiary,
//    onTertiary = clearSky_dark_onTertiary,
//    tertiaryContainer = clearSky_dark_tertiaryContainer,
//    onTertiaryContainer = clearSky_dark_onTertiaryContainer,
//    error = clearSky_dark_error,
//    errorContainer = clearSky_dark_errorContainer,
//    onError = clearSky_dark_onError,
//    onErrorContainer = clearSky_dark_onErrorContainer,
//    background = clearSky_dark_background,
//    onBackground = clearSky_dark_onBackground,
//    surface = clearSky_dark_surface,
//    onSurface = clearSky_dark_onSurface)

private val CloudyLightColorPalette = lightColorScheme(
    primary = cloudy_light_primary,
    onPrimary = cloudy_light_onPrimary,
    primaryContainer = cloudy_light_primaryContainer,
    onPrimaryContainer = cloudy_light_onPrimaryContainer,
    secondary = cloudy_light_secondary,
    onSecondary = cloudy_light_onSecondary,
    secondaryContainer = cloudy_light_secondaryContainer,
    onSecondaryContainer = cloudy_light_onSecondaryContainer,
    tertiary = cloudy_light_tertiary,
    onTertiary = cloudy_light_onTertiary,
    tertiaryContainer = cloudy_light_tertiaryContainer,
    onTertiaryContainer = cloudy_light_onTertiaryContainer,
    error = cloudy_light_error,
    errorContainer = cloudy_light_errorContainer,
    onError = cloudy_light_onError,
    onErrorContainer = cloudy_light_onErrorContainer,
    background = cloudy_light_background,
    onBackground = cloudy_light_onBackground,
    surface = cloudy_light_surface,
    onSurface = cloudy_light_onSurface
)

//private val CloudyDarkColors = darkColorScheme(
//    primary = cloudy_dark_primary,
//    onPrimary = cloudy_dark_onPrimary,
//    primaryContainer = cloudy_dark_primaryContainer,
//    onPrimaryContainer = cloudy_dark_onPrimaryContainer,
//    secondary = cloudy_dark_secondary,
//    onSecondary = cloudy_dark_onSecondary,
//    secondaryContainer = cloudy_dark_secondaryContainer,
//    onSecondaryContainer = cloudy_dark_onSecondaryContainer,
//    tertiary = cloudy_dark_tertiary,
//    onTertiary = cloudy_dark_onTertiary,
//    tertiaryContainer = cloudy_dark_tertiaryContainer,
//    onTertiaryContainer = cloudy_dark_onTertiaryContainer,
//    error = cloudy_dark_error,
//    errorContainer = cloudy_dark_errorContainer,
//    onError = cloudy_dark_onError,
//    onErrorContainer = cloudy_dark_onErrorContainer,
//    background = cloudy_dark_background,
//    onBackground = cloudy_dark_onBackground,
//    surface = cloudy_dark_surface,
//    onSurface = cloudy_dark_onSurface
//)

private val DrizzleLightColorPalette = lightColorScheme(
    primary = drizzle_light_primary,
    onPrimary = drizzle_light_onPrimary,
    primaryContainer = drizzle_light_primaryContainer,
    onPrimaryContainer = drizzle_light_onPrimaryContainer,
    secondary = drizzle_light_secondary,
    onSecondary = drizzle_light_onSecondary,
    secondaryContainer = drizzle_light_secondaryContainer,
    onSecondaryContainer = drizzle_light_onSecondaryContainer,
    tertiary = drizzle_light_tertiary,
    onTertiary = drizzle_light_onTertiary,
    tertiaryContainer = drizzle_light_tertiaryContainer,
    onTertiaryContainer = drizzle_light_onTertiaryContainer,
    error = drizzle_light_error,
    errorContainer = drizzle_light_errorContainer,
    onError = drizzle_light_onError,
    onErrorContainer = drizzle_light_onErrorContainer,
    background = drizzle_light_background,
    onBackground = drizzle_light_onBackground,
    surface = drizzle_light_surface,
    onSurface = drizzle_light_onSurface
)

//private val DrizzleDarkColors = darkColorScheme(
//    primary = drizzle_dark_primary,
//    onPrimary = drizzle_dark_onPrimary,
//    primaryContainer = drizzle_dark_primaryContainer,
//    onPrimaryContainer = drizzle_dark_onPrimaryContainer,
//    secondary = drizzle_dark_secondary,
//    onSecondary = drizzle_dark_onSecondary,
//    secondaryContainer = drizzle_dark_secondaryContainer,
//    onSecondaryContainer = drizzle_dark_onSecondaryContainer,
//    tertiary = drizzle_dark_tertiary,
//    onTertiary = drizzle_dark_onTertiary,
//    tertiaryContainer = drizzle_dark_tertiaryContainer,
//    onTertiaryContainer = drizzle_dark_onTertiaryContainer,
//    error = drizzle_dark_error,
//    errorContainer = drizzle_dark_errorContainer,
//    onError = drizzle_dark_onError,
//    onErrorContainer = drizzle_dark_onErrorContainer,
//    background = drizzle_dark_background,
//    onBackground = drizzle_dark_onBackground,
//    surface = drizzle_dark_surface,
//    onSurface = drizzle_dark_onSurface
//)


private val SnowLightColorPalette = lightColorScheme(
    primary = snow_light_primary,
    onPrimary = snow_light_onPrimary,
    primaryContainer = snow_light_primaryContainer,
    onPrimaryContainer = snow_light_onPrimaryContainer,
    secondary = snow_light_secondary,
    onSecondary = snow_light_onSecondary,
    secondaryContainer = snow_light_secondaryContainer,
    onSecondaryContainer = snow_light_onSecondaryContainer,
    tertiary = snow_light_tertiary,
    onTertiary = snow_light_onTertiary,
    tertiaryContainer = snow_light_tertiaryContainer,
    onTertiaryContainer = snow_light_onTertiaryContainer,
    error = snow_light_error,
    errorContainer = snow_light_errorContainer,
    onError = snow_light_onError,
    onErrorContainer = snow_light_onErrorContainer,
    background = snow_light_background,
    onBackground = snow_light_onBackground,
    surface = snow_light_surface,
    onSurface = snow_light_onSurface
)

//private val SnowDarkColors = darkColorScheme(
//    primary = snow_dark_primary,
//    onPrimary = snow_dark_onPrimary,
//    primaryContainer = snow_dark_primaryContainer,
//    onPrimaryContainer = snow_dark_onPrimaryContainer,
//    secondary = snow_dark_secondary,
//    onSecondary = snow_dark_onSecondary,
//    secondaryContainer = snow_dark_secondaryContainer,
//    onSecondaryContainer = snow_dark_onSecondaryContainer,
//    tertiary = snow_dark_tertiary,
//    onTertiary = snow_dark_onTertiary,
//    tertiaryContainer = snow_dark_tertiaryContainer,
//    onTertiaryContainer = snow_dark_onTertiaryContainer,
//    error = snow_dark_error,
//    errorContainer = snow_dark_errorContainer,
//    onError = snow_dark_onError,
//    onErrorContainer = snow_dark_onErrorContainer,
//    background = snow_dark_background,
//    onBackground = snow_dark_onBackground,
//    surface = snow_dark_surface,
//    onSurface = snow_dark_onSurface
//)

private val RainLightColorPalette = lightColorScheme(
    primary = rain_light_primary,
    onPrimary = rain_light_onPrimary,
    primaryContainer = rain_light_primaryContainer,
    onPrimaryContainer = rain_light_onPrimaryContainer,
    secondary = rain_light_secondary,
    onSecondary = rain_light_onSecondary,
    secondaryContainer = rain_light_secondaryContainer,
    onSecondaryContainer = rain_light_onSecondaryContainer,
    tertiary = rain_light_tertiary,
    onTertiary = rain_light_onTertiary,
    tertiaryContainer = rain_light_tertiaryContainer,
    onTertiaryContainer = rain_light_onTertiaryContainer,
    error = rain_light_error,
    errorContainer = rain_light_errorContainer,
    onError = rain_light_onError,
    onErrorContainer = rain_light_onErrorContainer,
    background = rain_light_background,
    onBackground = rain_light_onBackground,
    surface = rain_light_surface,
    onSurface = rain_light_onSurface
)


//private val RainDarkColors = darkColorScheme(
//    primary = rain_dark_primary,
//    onPrimary = rain_dark_onPrimary,
//    primaryContainer = rain_dark_primaryContainer,
//    onPrimaryContainer = rain_dark_onPrimaryContainer,
//    secondary = rain_dark_secondary,
//    onSecondary = rain_dark_onSecondary,
//    secondaryContainer = rain_dark_secondaryContainer,
//    onSecondaryContainer = rain_dark_onSecondaryContainer,
//    tertiary = rain_dark_tertiary,
//    onTertiary = rain_dark_onTertiary,
//    tertiaryContainer = rain_dark_tertiaryContainer,
//    onTertiaryContainer = rain_dark_onTertiaryContainer,
//    error = rain_dark_error,
//    errorContainer = rain_dark_errorContainer,
//    onError = rain_dark_onError,
//    onErrorContainer = rain_dark_onErrorContainer,
//    background = rain_dark_background,
//    onBackground = rain_dark_onBackground,
//    surface = rain_dark_surface,
//    onSurface = rain_dark_onSurface)


private val ThunderstormLightColorPalette = lightColorScheme(
    primary = thunderstorm_light_primary,
    onPrimary = thunderstorm_light_onPrimary,
    primaryContainer = thunderstorm_light_primaryContainer,
    onPrimaryContainer = thunderstorm_light_onPrimaryContainer,
    secondary = thunderstorm_light_secondary,
    onSecondary = thunderstorm_light_onSecondary,
    secondaryContainer = thunderstorm_light_secondaryContainer,
    onSecondaryContainer = thunderstorm_light_onSecondaryContainer,
    tertiary = thunderstorm_light_tertiary,
    onTertiary = thunderstorm_light_onTertiary,
    tertiaryContainer = thunderstorm_light_tertiaryContainer,
    onTertiaryContainer = thunderstorm_light_onTertiaryContainer,
    error = thunderstorm_light_error,
    errorContainer = thunderstorm_light_errorContainer,
    onError = thunderstorm_light_onError,
    onErrorContainer = thunderstorm_light_onErrorContainer,
    background = thunderstorm_light_background,
    onBackground = thunderstorm_light_onBackground,
    surface = thunderstorm_light_surface,
    onSurface = thunderstorm_light_onSurface
)


//private val ThunderstormDarkColors = darkColorScheme(
//    primary = thunderstorm_dark_primary,
//    onPrimary = thunderstorm_dark_onPrimary,
//    primaryContainer = thunderstorm_dark_primaryContainer,
//    onPrimaryContainer = thunderstorm_dark_onPrimaryContainer,
//    secondary = thunderstorm_dark_secondary,
//    onSecondary = thunderstorm_dark_onSecondary,
//    secondaryContainer = thunderstorm_dark_secondaryContainer,
//    onSecondaryContainer = thunderstorm_dark_onSecondaryContainer,
//    tertiary = thunderstorm_dark_tertiary,
//    onTertiary = thunderstorm_dark_onTertiary,
//    tertiaryContainer = thunderstorm_dark_tertiaryContainer,
//    onTertiaryContainer = thunderstorm_dark_onTertiaryContainer,
//    error = thunderstorm_dark_error,
//    errorContainer = thunderstorm_dark_errorContainer,
//    onError = thunderstorm_dark_onError,
//    onErrorContainer = thunderstorm_dark_onErrorContainer,
//    background = thunderstorm_dark_background,
//    onBackground = thunderstorm_dark_onBackground,
//    surface = thunderstorm_dark_surface,
//    onSurface = thunderstorm_dark_onSurface
//)

@Composable
fun WeatherAppTheme(
    isDarkTheme: Boolean = isSystemInDarkTheme(),
//    weatherType: WeatherType,
    content: @Composable () -> Unit
) {

//    val myColorScheme = when (weatherType) {
//        WeatherType.ClearSkyLightColor -> ClearSkyLightColorPalette
//        WeatherType.CloudyLightColor -> CloudyLightColorPalette
//        WeatherType.DrizzleLightColor -> DrizzleLightColorPalette
//        WeatherType.SnowLightColor -> SnowLightColorPalette
//        WeatherType.RainLightColor -> RainLightColorPalette
//        WeatherType.ThunderstormLightColor -> ThunderstormLightColorPalette
//    }
    MaterialTheme(
        colorScheme = ClearSkyLightColorPalette,
        typography = Typography,
        content = content
    )
}

enum class WeatherType {
    ClearSkyLightColor,
    CloudyLightColor,
    DrizzleLightColor,
    SnowLightColor,
    RainLightColor,
    ThunderstormLightColor
}