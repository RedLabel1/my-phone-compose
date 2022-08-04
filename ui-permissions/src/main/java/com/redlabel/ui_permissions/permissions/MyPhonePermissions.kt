package com.redlabel.ui_permissions.permissions

import android.app.role.RoleManager
import android.content.Context
import android.content.Intent
import android.os.Build
import android.telecom.TelecomManager
import androidx.lifecycle.DefaultLifecycleObserver
import dagger.hilt.android.qualifiers.ActivityContext
import kotlinx.coroutines.flow.*
import javax.inject.Inject

class MyPhonePermissions @Inject constructor(@ActivityContext private val context: Context) : DefaultLifecycleObserver {

    private val defaultDialerChangedFlow = MutableSharedFlow<Boolean>(extraBufferCapacity = 1)

    val isAppDefaultDialer = context.getSystemService(TelecomManager::class.java).defaultDialerPackage == context.packageName

    fun setDefaultDialer(isDefault: Boolean) = defaultDialerChangedFlow.tryEmit(isDefault)

    fun observeDefaultDialerGrant() = defaultDialerChangedFlow.asSharedFlow()

    fun getDefaultDialerIntent(context: Context) = when {
        Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q -> {
            context.getSystemService(RoleManager::class.java)?.createRequestRoleIntent(RoleManager.ROLE_DIALER)
        }
        else -> {
            val intent = Intent(TelecomManager.ACTION_CHANGE_DEFAULT_DIALER)
            intent.putExtra(TelecomManager.EXTRA_CHANGE_DEFAULT_DIALER_PACKAGE_NAME, context.packageName)
        }
    }
}
