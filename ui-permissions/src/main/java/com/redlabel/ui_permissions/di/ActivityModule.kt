package com.redlabel.ui_permissions.di

import android.content.Context
import com.redlabel.ui_permissions.permissions.MyPhonePermissions
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityComponent
import dagger.hilt.android.qualifiers.ActivityContext
import dagger.hilt.android.scopes.ActivityScoped

@InstallIn(ActivityComponent::class)
@Module
object ActivityModule {

    @Provides
    @ActivityScoped
    fun providePermissions(
        @ActivityContext context: Context
    ): MyPhonePermissions {
        return MyPhonePermissions(context)
    }
}
