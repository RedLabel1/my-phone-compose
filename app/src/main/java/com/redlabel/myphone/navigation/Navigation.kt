package com.redlabel.myphone.navigation

import androidx.annotation.StringRes
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.ui.graphics.vector.ImageVector
import com.redlabel.myphone.R

sealed class Screen(val route: String, val icon: ImageVector, @StringRes val resourceId: Int) {
    object Favorites : Screen("favorites", Icons.Filled.Star, R.string.route_favorites)
    object Recents : Screen("recents", Icons.Filled.Schedule, R.string.route_recents)
    object Contacts : Screen("contacts", Icons.Filled.AccountCircle, R.string.route_contacts)
    object Keypad : Screen("keypad", Icons.Filled.Dialpad, R.string.route_keypad)
    object Voicemail : Screen("voicemail", Icons.Filled.Voicemail, R.string.route_voicemail)
}
