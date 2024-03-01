package com.mk.api.di

import com.mk.api.di.assessment.service.PetFinderService
import com.mk.networking.di.AssessmentRetrofit
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit

@Module
@InstallIn(SingletonComponent::class)
object ServiceModule {
    @Provides
    fun provideStadiumService(@AssessmentRetrofit retrofit: Retrofit): PetFinderService =
        retrofit.create(PetFinderService::class.java)
}
