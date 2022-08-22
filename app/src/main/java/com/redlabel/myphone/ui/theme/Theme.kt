package com.redlabel.myphone.ui.theme

import android.content.Context
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.SideEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.TextStyle
import com.google.accompanist.systemuicontroller.rememberSystemUiController
import com.redlabel.myphone.configuration.preferences.MyPhonePreferences
import com.redlabel.myphone.configuration.preferences.MyPhoneTheme as Theme

private val DarkColorScheme = darkColorScheme(
    primary = Purple200,
    secondary = DeepPurple200,
    tertiary = Pink200,
    onSurface = Grey200
)

private val LightColorScheme = lightColorScheme(
    primary = Purple800,
    secondary = DeepPurple800,
    tertiary = Pink800,
    onSurface = Grey800
    /* Other default colors to override
    background = Color(0xFFFFFBFE),
    surface = Color(0xFFFFFBFE),
    onPrimary = Color.White,
    onSecondary = Color.White,
    onTertiary = Color.White,
    onBackground = Color(0xFF1C1B1F),
    onSurface = Color(0xFF1C1B1F),
    */
)

@Composable
fun MyPhoneTheme(
    colorSchemeWrapper: ColorSchemeWrapper,
    content: @Composable () -> Unit
) {
    val systemUiController = rememberSystemUiController()
    val useDarkIcons = !colorSchemeWrapper.isSystemInDarkTheme

    SideEffect {
        systemUiController.setSystemBarsColor(
            color = Color.Transparent,
            darkIcons = useDarkIcons
        )
        systemUiController.isNavigationBarContrastEnforced = false
    }

    MaterialTheme(
        colorScheme = colorSchemeWrapper.colorScheme,
        typography = Typography,
        content = {
            ProvideTextStyle(
                value = TextStyle(color = colorSchemeWrapper.colorScheme.onSurface),
                content = content
            )
        }
    )
}

@Composable
fun MyPhonePreferences.colorSchemeWrapper(isSystemInDarkTheme: Boolean = isSystemInDarkTheme()): ColorSchemeWrapper {
    val context = LocalContext.current
    val themeState = observeTheme().collectAsState(initial = Theme.SYSTEM)
    return when (themeState.value) {
        Theme.LIGHT -> ColorSchemeWrapper(colorScheme = LightColorScheme, isSystemInDarkTheme = false)
        Theme.DARK -> ColorSchemeWrapper(colorScheme = DarkColorScheme, isSystemInDarkTheme = true)
        Theme.SYSTEM -> ColorSchemeWrapper(getSystemColorScheme(isSystemInDarkTheme), isSystemInDarkTheme)
        Theme.DYNAMIC -> ColorSchemeWrapper(getDynamicColorScheme(context, isSystemInDarkTheme), isSystemInDarkTheme)
    }
}

private fun getSystemColorScheme(isSystemInDarkTheme: Boolean) = when (isSystemInDarkTheme) {
    true -> DarkColorScheme
    false -> LightColorScheme
}

private fun getDynamicColorScheme(context: Context, isSystemInDarkTheme: Boolean) = when (isSystemInDarkTheme) {
    true -> dynamicDarkColorScheme(context)
    false -> dynamicLightColorScheme(context)
}

data class ColorSchemeWrapper(val colorScheme: ColorScheme, val isSystemInDarkTheme: Boolean)
