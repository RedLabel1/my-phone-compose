package com.redlabel.ui_common.util

import androidx.compose.animation.core.FastOutLinearInEasing
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.lerp

@Composable
fun containerColor(containerColor: Color, scrolledContainerColor: Color, colorTransitionFraction: Float): Color {
    return lerp(
        containerColor,
        scrolledContainerColor,
        FastOutLinearInEasing.transform(colorTransitionFraction)
    )
}
