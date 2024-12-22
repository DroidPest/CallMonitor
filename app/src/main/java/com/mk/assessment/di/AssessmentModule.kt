package com.mk.assessment.di

import android.content.Context
import callLogs.ContactLoggerManager
import callLogs.ContactLoggerManagerImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AssessmentModule {
    @Singleton
    @Provides
    fun provideContactLoggerManager(
        @ApplicationContext context: Context,
    ): ContactLoggerManager = ContactLoggerManagerImpl(context)
}
