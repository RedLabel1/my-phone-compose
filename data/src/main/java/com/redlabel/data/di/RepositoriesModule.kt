package com.redlabel.data.di

import android.content.Context
import com.redlabel.data.repositories.ContactsRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
object RepositoriesModule {

    @Singleton
    @Provides
    fun provideContactsRepository(@ApplicationContext context: Context): ContactsRepository = ContactsRepository(context)
}
