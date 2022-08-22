package com.redlabel.myphone

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material3.Surface
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.core.view.WindowCompat
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

        WindowCompat.setDecorFitsSystemWindows(window, false)

        setContent {
            MyPhoneTheme(preferences.colorSchemeWrapper()) {

                Box(modifier = Modifier.fillMaxSize()) {

                    val isAppDefaultDialer =
                        permissions.observeDefaultDialerGrant().collectAsState(initial = permissions.isAppDefaultDialer)

                    Image(
                        modifier = Modifier.fillMaxSize(),
                        painter = painterResource(id = R.drawable.light_background),
                        contentScale = ContentScale.FillBounds,
                        contentDescription = null
                    )

                    Surface(modifier = Modifier.fillMaxSize(), color = Color.Transparent) {
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
