package com.redlabel.myphone

import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.navigation.NavDestination.Companion.hierarchy
import androidx.navigation.compose.currentBackStackEntryAsState
import com.google.accompanist.navigation.animation.AnimatedNavHost
import com.google.accompanist.navigation.animation.composable
import com.google.accompanist.navigation.animation.rememberAnimatedNavController
import com.redlabel.myphone.configuration.preferences.MyPhonePreferences
import com.redlabel.myphone.configuration.preferences.StartDestination
import com.redlabel.myphone.navigation.Screen
import com.redlabel.ui_contacts.Contacts
import com.redlabel.ui_favorites.Favorites
import com.redlabel.ui_keypad.Keypad
import com.redlabel.ui_recents.Recents
import com.redlabel.ui_voicemail.Voicemail

private val navigationBarItems = listOf(
    Screen.Favorites,
    Screen.Recents,
    Screen.Contacts,
    Screen.Keypad,
    Screen.Voicemail
)

@OptIn(ExperimentalMaterial3Api::class, ExperimentalAnimationApi::class)
@Composable
fun Home(preferences: MyPhonePreferences) {

    val navController = rememberAnimatedNavController()

    LaunchedEffect(navController) {
        navController.currentBackStackEntryFlow.collect { entry ->
            entry.destination.route?.let { preferences.startDestination = StartDestination.valueOf(it.uppercase()) }
        }
    }

    Scaffold(
        bottomBar = {
            NavigationBar {
                val navBackStackEntry by navController.currentBackStackEntryAsState()
                val currentDestination = navBackStackEntry?.destination
                navigationBarItems.forEach { screen ->
                    val screenName = stringResource(screen.resourceId)
                    NavigationBarItem(
                        icon = { Icon(screen.icon, contentDescription = screenName) },
                        selected = currentDestination?.hierarchy?.any { it.route == screen.route } == true,
                        label = { Text(screenName) },
                        onClick = {
                            navController.navigate(screen.route) {
                                currentDestination?.let {
                                    popUpTo(it.id) {
                                        inclusive = true
                                        saveState = true
                                    }
                                    launchSingleTop = true
                                    restoreState = true
                                }
                            }
                        }
                    )
                }
            }
        }
    ) { innerPadding ->
        AnimatedNavHost(navController, startDestination = preferences.startDestination.name.lowercase(), Modifier.padding(innerPadding)) {
            composable(Screen.Favorites.route) { Favorites() }
            composable(Screen.Recents.route) { Recents() }
            composable(Screen.Contacts.route) { Contacts() }
            composable(Screen.Keypad.route) { Keypad() }
            composable(Screen.Voicemail.route) { Voicemail() }
        }
    }
}
