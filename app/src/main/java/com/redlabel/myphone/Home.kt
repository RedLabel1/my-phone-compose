package com.redlabel.myphone

import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.navigation.compose.currentBackStackEntryAsState
import com.google.accompanist.navigation.animation.AnimatedNavHost
import com.google.accompanist.navigation.animation.composable
import com.google.accompanist.navigation.animation.rememberAnimatedNavController
import com.redlabel.myphone.configuration.preferences.MyPhonePreferences
import com.redlabel.myphone.configuration.preferences.StartDestination
import com.redlabel.myphone.navigation.Screen
import com.redlabel.ui_contacts.Contacts
import com.redlabel.ui_favorites.Favorites
import com.redlabel.ui_recents.Recents
import com.redlabel.ui_voicemail.Voicemail
import kotlinx.coroutines.ExperimentalCoroutinesApi

private val navigationBarItems = listOf(
    Screen.Favorites,
    Screen.Recents,
    Screen.Contacts,
    Screen.Voicemail
)

@ExperimentalMaterial3Api
@ExperimentalAnimationApi
@ExperimentalCoroutinesApi
@Composable
fun Home(preferences: MyPhonePreferences, modifier: Modifier = Modifier) {

    val navController = rememberAnimatedNavController()
    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentDestination = navBackStackEntry?.destination

    LaunchedEffect(navController) {
        navController.currentBackStackEntryFlow.collect { entry ->
            entry.destination.route?.let { preferences.startDestination = StartDestination.valueOf(it.uppercase()) }
        }
    }

    Scaffold(
        modifier = modifier,
        bottomBar = {
            BottomAppBar(
                actions = {
                    Row {
                        navigationBarItems.forEach { screen ->
                            IconToggleButton(
                                checked = currentDestination?.route == screen.route,
                                onCheckedChange = {
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
                                }) {
                                Icon(imageVector = screen.icon, contentDescription = stringResource(id = screen.resourceId))
                            }
                        }
                    }
                },
                floatingActionButton = {
                    FloatingActionButton(
                        elevation = FloatingActionButtonDefaults.bottomAppBarFabElevation(),
                        onClick = {

                        }) {
                        Icon(imageVector = Screen.Keypad.icon, contentDescription = stringResource(id = Screen.Keypad.resourceId))
                    }
                }
            )
        }
    ) { innerPadding ->
        AnimatedNavHost(
            navController = navController,
            startDestination = preferences.startDestination.name.lowercase(),
            modifier = Modifier.padding(bottom = innerPadding.calculateBottomPadding())
        ) {
            composable(Screen.Favorites.route) { Favorites() }
            composable(Screen.Recents.route) { Recents() }
            composable(Screen.Contacts.route) { Contacts() }
            composable(Screen.Voicemail.route) { Voicemail() }
        }
    }
}
