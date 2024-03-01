package com.mk.networking.di

import com.mk.networking.authentication.service.TokenService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit

@Module
@InstallIn(SingletonComponent::class)
class ServiceModule {

    @Provides
    fun provideRefreshTokenService(
        @AuthenticationRetrofit retrofit: Retrofit,
    ): TokenService = retrofit.create(TokenService::class.java)
}
