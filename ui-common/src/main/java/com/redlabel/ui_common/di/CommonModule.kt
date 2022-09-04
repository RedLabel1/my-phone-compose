package com.redlabel.ui_common.di

import com.redlabel.ui_common.model.UiMessageManager
import com.redlabel.ui_common.util.ObservableLoadingCounter
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent

@InstallIn(ViewModelComponent::class)
@Module
object RepositoriesModule {

    @Provides
    fun provideObservableLoadingCounter(): ObservableLoadingCounter = ObservableLoadingCounter()

    @Provides
    fun provideUiMessageManager(): UiMessageManager = UiMessageManager()
}
