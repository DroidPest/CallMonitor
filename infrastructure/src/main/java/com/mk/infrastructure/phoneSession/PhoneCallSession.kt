package com.mk.infrastructure.phoneSession

data class PhoneCallSession(
    val ongoing: Boolean,
    val phoneNumber: String,
    val contactName: String,
)
