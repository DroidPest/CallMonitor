package com.mk.networking.di

import com.mk.networking.Environment
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object CommonModule {

    /**
     There is no need to change environments so just return PROD for now
     */
    @Singleton
    @Provides
    fun provideEnvironment(): Environment = Environment.PROD
}
