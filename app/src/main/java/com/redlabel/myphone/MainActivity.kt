package com.redlabel.myphone

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.foundation.layout.*
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Surface
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.core.view.WindowCompat
import com.redlabel.myphone.configuration.preferences.MyPhonePreferences
import com.redlabel.myphone.ui.theme.*
import com.redlabel.ui_permissions.Permissions
import com.redlabel.ui_permissions.permissions.MyPhonePermissions
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.ExperimentalCoroutinesApi
import javax.inject.Inject

@ExperimentalMaterial3Api
@ExperimentalAnimationApi
@ExperimentalCoroutinesApi
@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    @Inject
    internal lateinit var preferences: MyPhonePreferences

    @Inject
    internal lateinit var permissions: MyPhonePermissions

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        WindowCompat.setDecorFitsSystemWindows(window, false)

        setContent {
            MyPhoneTheme(preferences.colorSchemeWrapper()) {

                Box(modifier = Modifier.fillMaxSize()) {

                    val isAppDefaultDialer =
                        permissions.observeDefaultDialerGrant().collectAsState(initial = permissions.isAppDefaultDialer)

                    Surface(modifier = Modifier.fillMaxSize()) {
                        if (isAppDefaultDialer.value) {
                            Home(preferences, modifier = Modifier.navigationBarsPadding().systemBarsPadding())
                        } else {
                            Permissions(permissions, modifier = Modifier.navigationBarsPadding().systemBarsPadding())
                        }
                    }
                }
            }
        }
    }
}
