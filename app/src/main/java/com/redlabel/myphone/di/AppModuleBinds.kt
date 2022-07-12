package com.redlabel.myphone.di

import com.redlabel.myphone.configuration.initializers.AppInitializer
import com.redlabel.myphone.configuration.initializers.EmojiInitializer
import com.redlabel.myphone.configuration.initializers.PreferencesInitializer
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import dagger.multibindings.IntoSet

@InstallIn(SingletonComponent::class)
@Module
abstract class AppModuleBinds {

    @Binds
    @IntoSet
    abstract fun provideEmojiInitializer(bind: EmojiInitializer): AppInitializer

    @Binds
    @IntoSet
    abstract fun providePreferencesInitializer(bind: PreferencesInitializer): AppInitializer
}
