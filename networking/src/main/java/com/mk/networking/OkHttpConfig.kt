package com.mk.networking

import okhttp3.OkHttpClient

fun interface OkHttpConfig {
    fun configure(builder: OkHttpClient.Builder)

    companion object {
        fun OkHttpClient.Builder.buildWithConfig(okHttpConfig: OkHttpConfig): OkHttpClient {
            okHttpConfig.configure(this)
            return build()
        }

        operator fun OkHttpConfig.plus(other: OkHttpConfig): OkHttpConfig = OkHttpConfig { builder ->
            configure(builder)
            other.configure(builder)
        }
    }
}
