package com.redlabel.ui_permissions

import androidx.activity.ComponentActivity
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.layout.*
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.redlabel.ui_permissions.permissions.MyPhonePermissions

@Composable
fun Permissions(permissions: MyPhonePermissions) {

    val context = LocalContext.current
    val launcher = rememberLauncherForActivityResult(contract = ActivityResultContracts.StartActivityForResult()) {
        permissions.setDefaultDialer(it.resultCode == ComponentActivity.RESULT_OK)
    }

    Box(
        modifier = Modifier
            .height(60.dp)
            .fillMaxSize()
    ) {
        OutlinedButton(
            modifier = Modifier
                .padding(vertical = 24.dp)
                .wrapContentWidth()
                .align(Alignment.BottomCenter),
            onClick = { launcher.launch(permissions.getDefaultDialerIntent(context)) }) {
            Text(text = stringResource(id = R.string.set_as_default))
        }
    }
}
