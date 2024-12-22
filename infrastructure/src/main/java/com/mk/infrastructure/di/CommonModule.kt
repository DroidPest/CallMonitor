package com.mk.infrastructure.di

import android.content.Context
import android.net.ConnectivityManager
import com.mk.infrastructure.AppSessionManager
import com.mk.infrastructure.AssessmentAppSessionManager
import com.mk.infrastructure.phoneSession.PhoneCallSessionManager
import com.mk.infrastructure.phoneSession.PhoneCallSessionManagerImpl
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class CommonModule {
    companion object {
        @Singleton
        @Provides
        fun provideConnectivityService(@ApplicationContext context: Context): ConnectivityManager =
            context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
    }

    @Singleton
    @Binds
    abstract fun provideSessionManager(AppSessionManager: AssessmentAppSessionManager): AppSessionManager

    @Singleton
    @Binds
    abstract fun providePhoneCallSessionManager(phoneCallSessionManager: PhoneCallSessionManagerImpl): PhoneCallSessionManager
}
