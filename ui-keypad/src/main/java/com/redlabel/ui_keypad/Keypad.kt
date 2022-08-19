package com.redlabel.ui_keypad

import androidx.annotation.StringRes
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

private val dialpadGridItems = listOf<@Composable () -> Unit>(
    { DialButton(R.string.dialpad_digit_1, R.string.dialpad_aux_1) },
    { DialButton(R.string.dialpad_digit_2, R.string.dialpad_aux_2) },
    { DialButton(R.string.dialpad_digit_3, R.string.dialpad_aux_3) },
    { DialButton(R.string.dialpad_digit_4, R.string.dialpad_aux_4) },
    { DialButton(R.string.dialpad_digit_5, R.string.dialpad_aux_5) },
    { DialButton(R.string.dialpad_digit_6, R.string.dialpad_aux_6) },
    { DialButton(R.string.dialpad_digit_7, R.string.dialpad_aux_7) },
    { DialButton(R.string.dialpad_digit_8, R.string.dialpad_aux_8) },
    { DialButton(R.string.dialpad_digit_9, R.string.dialpad_aux_9) },
    { DialButton(R.string.dialpad_digit_star) },
    { DialButton(R.string.dialpad_digit_0, R.string.dialpad_aux_0) },
    { DialButton(R.string.dialpad_digit_number_sign) },
    { DialButton(R.string.dialpad_digit_7, R.string.dialpad_aux_7) },
    { DialButton(R.string.dialpad_digit_8, R.string.dialpad_aux_8) },
    { DialButton(R.string.dialpad_digit_9, R.string.dialpad_aux_9) },
)

@Composable
fun Keypad() {
    val measurements = LocalConfiguration.current
    Box(modifier = Modifier
        .fillMaxHeight()
        .fillMaxWidth()) {
        Column(modifier = Modifier.wrapContentHeight().width(301.dp).align(Alignment.BottomCenter).padding(16.dp)) {
            Box(modifier = Modifier.height(80.dp).fillMaxWidth().background(Color.Blue))
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .align(Alignment.CenterHorizontally)
            ) {
                LazyVerticalGrid(
                    columns = GridCells.Fixed(3),
                    userScrollEnabled = false
                ) {
                    items(dialpadGridItems) { dialpadGridItem ->
                        dialpadGridItem()
                    }
                }
            }
        }
    }
}

@Composable
fun DialButton(@StringRes digit: Int, @StringRes aux: Int? = null) {
    // (1.5*measurements.screenHeightDp.toFloat())/770f
    Box(modifier = Modifier.aspectRatio(1.5f)) {
        Button(
            onClick = { },
            shape = CircleShape,
            modifier = Modifier
                .aspectRatio(1f)
                .align(Alignment.Center)
                .padding(2.dp)
        ) {
            Column {
                Box(Modifier.align(Alignment.CenterHorizontally)) {
                    Text(text = stringResource(id = digit), fontSize = 34.sp)
                }
                aux?.let {
                    Box(Modifier.align(Alignment.CenterHorizontally)) {
                        Text(text = stringResource(id = it), fontSize = 10.sp)
                    }
                }
            }
        }
    }
}

//private fun findkeypadMeasurements(configuration: Configuration): KeypadMeasurements {
//    return KeypadMeasurements(width = 1, height = 1, digitSize = 1, auxSize = 1)
//}
//
//data class KeypadMeasurements(val width: Int, val height: Int, val digitSize: Int, val auxSize: Int)

//@Composable
//fun Keypad() {
//    Box(
//        modifier = Modifier
//            .fillMaxHeight()
//            .fillMaxWidth()
//            .padding(horizontal = 24.dp, vertical = 8.dp)
//    ) {
//        Column(modifier = Modifier.align(Alignment.BottomCenter)) {
//            val config = LocalConfiguration.current
//            Box(
//                Modifier
//                    .fillMaxHeight(0.2f)
//                    .fillMaxWidth()
//            ) {}
//            Box {
//                Column(modifier = Modifier.padding(0.dp)) {
//                    Row(
//                        horizontalArrangement = Arrangement.SpaceEvenly, modifier = Modifier
//                            .fillMaxWidth()
//                            .weight(1f)
//                    ) {
//                            DialButton(R.string.dialpad_digit_1, R.string.dialpad_aux_1)
//                            DialButton(R.string.dialpad_digit_2, R.string.dialpad_aux_2)
//                            DialButton(R.string.dialpad_digit_3, R.string.dialpad_aux_3)
//                    }
//                    Row(
//                        horizontalArrangement = Arrangement.SpaceEvenly, modifier = Modifier
//                            .fillMaxWidth()
//                            .weight(1f)
//                    ) {
//                            DialButton(R.string.dialpad_digit_4, R.string.dialpad_aux_4)
//                            DialButton(R.string.dialpad_digit_5, R.string.dialpad_aux_5)
//                            DialButton(R.string.dialpad_digit_6, R.string.dialpad_aux_6)
//                    }
//                    Row(
//                        horizontalArrangement = Arrangement.SpaceEvenly, modifier = Modifier
//                            .fillMaxWidth()
//                            .weight(1f)
//                    ) {
//                            DialButton(R.string.dialpad_digit_7, R.string.dialpad_aux_7)
//                            DialButton(R.string.dialpad_digit_8, R.string.dialpad_aux_8)
//                            DialButton(R.string.dialpad_digit_9, R.string.dialpad_aux_9)
//                    }
//                    Row(
//                        horizontalArrangement = Arrangement.SpaceEvenly, modifier = Modifier
//                            .fillMaxWidth()
//                            .weight(1f)
//                    ) {
//                            DialButton(R.string.dialpad_digit_star)
//                            DialButton(R.string.dialpad_digit_0, R.string.dialpad_aux_0)
//                            DialButton(R.string.dialpad_digit_number_sign)
//                    }
//                    Row(
//                        horizontalArrangement = Arrangement.SpaceEvenly, modifier = Modifier
//                            .fillMaxWidth()
//                            .weight(1f)
//                    ) {
//                            DialButton(R.string.dialpad_digit_4, R.string.dialpad_aux_4)
//                            DialButton(R.string.dialpad_digit_5, R.string.dialpad_aux_5)
//                            DialButton(R.string.dialpad_digit_6, R.string.dialpad_aux_6)
//                    }
//                }
//            }
//        }
//    }
//}
//
//@Composable
//fun DialButton(@StringRes digit: Int, @StringRes aux: Int? = null) {
//    Box(contentAlignment = Alignment.Center, modifier = Modifier.background(Color.Red)) {
//        Button(
//            onClick = { },
//            shape = CircleShape,
//            modifier = Modifier.aspectRatio(1f).padding(4.dp)
//        ) {
//            Column {
//                Box(Modifier.align(Alignment.CenterHorizontally)) {
//                    Text(text = stringResource(id = digit), fontSize = 24.sp)
//                }
//                aux?.let {
//                    Box(Modifier.align(Alignment.CenterHorizontally)) {
//                        Text(text = stringResource(id = it), fontSize = 8.sp)
//                    }
//                }
//            }
//        }
//    }
//}
