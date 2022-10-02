package com.redlabel.myphone.ui.theme

import android.content.Context
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.ColorScheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.dynamicDarkColorScheme
import androidx.compose.material3.dynamicLightColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.SideEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import com.google.accompanist.systemuicontroller.rememberSystemUiController
import com.redlabel.myphone.configuration.preferences.MyPhonePreferences
import com.redlabel.myphone.configuration.preferences.MyPhoneTheme as Theme

private val DarkColorScheme = darkColorScheme(
    primary = DeepPurple200_20_shade,
    primaryContainer = DeepPurple200_60_shade,
    onSurface = Grey200,
    //    surfaceVariant = SemiTransparentPurple200
)

private val LightColorScheme = lightColorScheme(
    primary = DeepPurple400,
    primaryContainer = DeepPurple100,
    onSurface = Grey800,
//    surfaceVariant = SemiTransparentPurple800
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
        typography = myPhoneTypography(LocalContext.current, colorSchemeWrapper),
        content = content
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
