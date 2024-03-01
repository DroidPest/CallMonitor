package com.mk.networking.di

import com.mk.networking.Environment
import com.mk.networking.NetRequestStatusCallAdapter
import com.mk.networking.NetRequestStatusTypeConverter
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Qualifier
import javax.inject.Singleton

@Retention(AnnotationRetention.BINARY)
@Qualifier
annotation class AssessmentRetrofit

@Qualifier
@Retention(AnnotationRetention.RUNTIME)
annotation class AuthenticationRetrofit

@Module
@InstallIn(SingletonComponent::class)
class RetrofitModule {

    @Singleton
    @Provides
    @AssessmentRetrofit
    fun provideAssessmentRetrofit(
        @OkhttpClientAssessment client: OkHttpClient,
        environment: Environment,
    ): Retrofit =
        Retrofit.Builder()
            .baseUrl(environment.env.baseUrl)
            .addCallAdapterFactory(NetRequestStatusCallAdapter.Factory)
            .addConverterFactory(NetRequestStatusTypeConverter.Factory)
            .client(client)
            .addConverterFactory(GsonConverterFactory.create())
            .build()

    @Singleton
    @Provides
    @AuthenticationRetrofit
    fun provideAuthenticationRetrofit(
        @AuthenticationOkHttp client: OkHttpClient,
        environment: Environment,
    ): Retrofit =
        Retrofit.Builder()
            .baseUrl(environment.env.baseUrl)
            .addCallAdapterFactory(NetRequestStatusCallAdapter.Factory)
            .addConverterFactory(NetRequestStatusTypeConverter.Factory)
            .client(client)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
}
