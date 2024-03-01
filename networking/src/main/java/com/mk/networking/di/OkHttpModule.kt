package com.mk.networking.di

import com.mk.networking.BuildConfig
import com.mk.networking.OkHttpConfig
import com.mk.networking.OkHttpConfig.Companion.buildWithConfig
import com.mk.networking.interceptor.AuthenticationInterceptor
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import java.util.concurrent.TimeUnit
import javax.inject.Qualifier
import javax.inject.Singleton

const val API_TIMEOUT = 60L

@Retention(AnnotationRetention.BINARY)
@Qualifier
annotation class OkhttpClientAssessment

@Retention(AnnotationRetention.BINARY)
@Qualifier
annotation class OkhttpClientDefault

@Qualifier
@Retention(AnnotationRetention.RUNTIME)
annotation class AuthenticationOkHttp

@Module
@InstallIn(SingletonComponent::class)
class OkHttpModule {

    @Singleton
    @Provides
    fun provideHttpLogger(): HttpLoggingInterceptor {
        val logger = HttpLoggingInterceptor.Logger.DEFAULT
        val level = if (BuildConfig.DEBUG) {
            HttpLoggingInterceptor.Level.BODY
        } else {
            HttpLoggingInterceptor.Level.NONE
        }
        return HttpLoggingInterceptor(logger).also { it.level = level }
    }

    @Singleton
    @Provides
    fun provideOkHttpConfig(
        httpLoggingInterceptor: HttpLoggingInterceptor,
    ): OkHttpConfig {
        return OkHttpConfig { builder -> builder.addInterceptor(httpLoggingInterceptor) }
    }

    @Singleton
    @Provides
    @OkhttpClientAssessment
    fun provideOkhttpClientAssessment(
        okHttpConfig: OkHttpConfig,
        authenticationInterceptor: AuthenticationInterceptor,
    ): OkHttpClient {
        val httpClient = OkHttpClient.Builder()
            .connectTimeout(API_TIMEOUT, TimeUnit.SECONDS)
            .readTimeout(API_TIMEOUT, TimeUnit.SECONDS)
            .addInterceptor(authenticationInterceptor)

        return httpClient.buildWithConfig(okHttpConfig)
    }

    @Singleton
    @Provides
    @OkhttpClientDefault
    fun provideOkhttpClientDefault(
        okHttpConfig: OkHttpConfig,
    ): OkHttpClient {
        val httpClient = OkHttpClient.Builder()
            .connectTimeout(API_TIMEOUT, TimeUnit.SECONDS)
            .readTimeout(API_TIMEOUT, TimeUnit.SECONDS)

        return httpClient.buildWithConfig(okHttpConfig)
    }

    @Singleton
    @Provides
    @AuthenticationOkHttp
    fun provideAuthenticationOkhttpClient(
        okHttpConfig: OkHttpConfig,
    ): OkHttpClient {
        val httpClient = OkHttpClient.Builder()
        return httpClient
            .buildWithConfig(okHttpConfig)
    }
}
