package com.mk.infrastructure.models

data class UserContactData(
    val beginning: String,
    val duration: String,
    val number: String,
    val contactName: String,
    val callerName: String,
    val timesQueried: Int,
)
