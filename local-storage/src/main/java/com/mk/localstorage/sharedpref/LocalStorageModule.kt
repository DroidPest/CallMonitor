package com.mk.localstorage.sharedpref

import android.content.Context
import android.content.SharedPreferences
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class LocalStorageModule {
    companion object {
        @Singleton
        @Provides
        fun provideSharedPreferences(@ApplicationContext context: Context): SharedPreferences =
            context.getSharedPreferences("assessment-prefs", Context.MODE_PRIVATE)
    }

    @Singleton
    @Binds
    abstract fun provideAssessmentSharedPreferenceManager(
        assessmentSharedPreferencesManager: AssessmentSharedPreferencesManager,
    ): SharedPreferencesManager
}
