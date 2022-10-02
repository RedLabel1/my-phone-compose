package com.redlabel.myphone.ui.theme

import android.content.Context
import android.graphics.Typeface
import androidx.compose.material3.Typography
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.unit.sp


fun myPhoneTypography(context: Context, colorSchemeWrapper: ColorSchemeWrapper): Typography {

    val typeFaceLight = Typeface.Builder(context.assets, "fonts/roboto_flex.ttf").apply {
        setFontVariationSettings("'wght' 200, 'opsz' 1")
    }.build()

    val typeFaceBold = Typeface.Builder(context.assets, "fonts/roboto_flex.ttf").apply {
        setFontVariationSettings("'wght' 500")
    }.build()

    return Typography(
        bodyLarge = TextStyle(
            fontFamily = FontFamily(typeFaceLight),
            fontSize = 18.sp
        ),
        bodyMedium = TextStyle(
            fontFamily = FontFamily(typeFaceLight),
            fontSize = 16.sp,
            color = colorSchemeWrapper.colorScheme.onSurface
        ),
        bodySmall = TextStyle(
            fontFamily = FontFamily(typeFaceLight),
            fontSize = 14.sp,
            color = colorSchemeWrapper.colorScheme.onSurface
        ),
        labelLarge = TextStyle(
            fontFamily = FontFamily(typeFaceBold),
            fontSize = 18.sp,
            color = colorSchemeWrapper.colorScheme.primary
        ),
        /* Other default text styles to override
        titleLarge = TextStyle(
            fontFamily = FontFamily.Default,
            fontWeight = FontWeight.Normal,
            fontSize = 22.sp,
            lineHeight = 28.sp,
            letterSpacing = 0.sp
        ),
        labelSmall = TextStyle(
            fontFamily = FontFamily.Default,
            fontWeight = FontWeight.Medium,
            fontSize = 11.sp,
            lineHeight = 16.sp,
            letterSpacing = 0.5.sp
        )
        */
    )
}
