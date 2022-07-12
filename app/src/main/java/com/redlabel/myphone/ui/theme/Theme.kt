package com.redlabel.myphone.ui.theme

import android.content.Context
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.SideEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.platform.LocalContext
import com.google.accompanist.systemuicontroller.rememberSystemUiController
import com.redlabel.myphone.configuration.preferences.MyPhonePreferences

enum class MyPhoneTheme {
    LIGHT,
    DARK,
    SYSTEM,
    DYNAMIC
}

private val DarkColorScheme = darkColorScheme(
    primary = Purple80,
    secondary = PurpleGrey80,
    tertiary = Pink80
)

private val LightColorScheme = lightColorScheme(
    primary = Purple40,
    secondary = PurpleGrey40,
    tertiary = Pink40

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
            color = colorSchemeWrapper.colorScheme.background,
            darkIcons = useDarkIcons
        )
    }

    MaterialTheme(
        colorScheme = colorSchemeWrapper.colorScheme,
        typography = Typography,
        content = content
    )
}

@Composable
fun MyPhonePreferences.colorSchemeWrapper(isSystemInDarkTheme: Boolean = isSystemInDarkTheme()): ColorSchemeWrapper {
    val context = LocalContext.current
    val themeState = observeTheme().collectAsState(initial = MyPhoneTheme.SYSTEM)
    return when (themeState.value) {
        MyPhoneTheme.LIGHT -> ColorSchemeWrapper(colorScheme = LightColorScheme, isSystemInDarkTheme = false)
        MyPhoneTheme.DARK -> ColorSchemeWrapper(colorScheme = DarkColorScheme, isSystemInDarkTheme = true)
        MyPhoneTheme.SYSTEM -> ColorSchemeWrapper(getSystemColorScheme(isSystemInDarkTheme), isSystemInDarkTheme)
        MyPhoneTheme.DYNAMIC -> ColorSchemeWrapper(getDynamicColorScheme(context, isSystemInDarkTheme), isSystemInDarkTheme)
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
