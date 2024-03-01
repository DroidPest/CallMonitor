package com.mk.networking

interface EnvironmentEndpoints {
    val baseUrl: String
        get() = "${BuildConfig.BASE_URL}/v2/"
}

sealed interface AssessmentEnvironment : EnvironmentEndpoints {
    data object PROD : AssessmentEnvironment
    data object DEV : AssessmentEnvironment
}

enum class Environment(val env: AssessmentEnvironment) {
    PROD(AssessmentEnvironment.PROD),
    DEV(AssessmentEnvironment.DEV),
}
