package com.redlabel.myphone

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Surface
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import com.redlabel.myphone.configuration.preferences.MyPhonePreferences
import com.redlabel.myphone.ui.theme.*
import com.redlabel.ui_permissions.Permissions
import com.redlabel.ui_permissions.permissions.MyPhonePermissions
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    @Inject
    internal lateinit var preferences: MyPhonePreferences

    @Inject
    internal lateinit var permissions: MyPhonePermissions

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MyPhoneTheme(preferences.colorSchemeWrapper()) {

                val isAppDefaultDialer = permissions.observeDefaultDialerGrant().collectAsState(initial = permissions.isAppDefaultDialer)

                // A surface container using the 'background' color from the theme
                Surface(modifier = Modifier.fillMaxSize()) {
                    if (isAppDefaultDialer.value) {
                        Home(preferences)
                    } else {
                        Permissions(permissions)
                    }
                }
            }
        }
    }
}
