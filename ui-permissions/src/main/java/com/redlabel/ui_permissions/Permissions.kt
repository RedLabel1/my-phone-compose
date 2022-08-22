package com.redlabel.ui_permissions

import androidx.activity.ComponentActivity
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.redlabel.ui_permissions.permissions.MyPhonePermissions

@Composable
fun Permissions(permissions: MyPhonePermissions, modifier: Modifier = Modifier) {

    val context = LocalContext.current
    val launcher = rememberLauncherForActivityResult(contract = ActivityResultContracts.StartActivityForResult()) {
        permissions.setDefaultDialer(it.resultCode == ComponentActivity.RESULT_OK)
    }

    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(24.dp),
        verticalArrangement = Arrangement.SpaceBetween,
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {

        Box(modifier = Modifier.weight(1f)) {
            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                modifier = Modifier.align(Alignment.Center)
            ) {
                Image(
                    painter = painterResource(id = com.redlabel.ui_common.R.drawable.my_phone_icon),
                    contentDescription = null,
                    modifier = Modifier.size(100.dp)
                )
                Text(text = stringResource(id = R.string.set_as_default_message), modifier = Modifier.padding(24.dp))
            }
        }

        Box(modifier = modifier
            .wrapContentHeight()
            .wrapContentWidth()) {
            OutlinedButton(
                modifier = modifier.padding(8.dp),
                onClick = { launcher.launch(permissions.getDefaultDialerIntent(context)) }) {
                Text(text = stringResource(id = R.string.set_as_default), color = MaterialTheme.colorScheme.primary)
            }
        }
    }
}
