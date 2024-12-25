package com.mk.infrastructure.phoneSession

data class PhoneCallSession(
    val ongoing: Boolean = false,
    val phoneNumber: String = "",
    val contactName: String = "",
)
