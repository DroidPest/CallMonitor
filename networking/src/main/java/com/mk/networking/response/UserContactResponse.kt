package com.mk.networking.response

import com.mk.infrastructure.models.UserContactData

data class UserContactDataResponse(
    val beginning: String,
    val duration: String,
    val number: String,
    val name: String,
    val timesQueried: Int,
)

fun List<UserContactData>.toDataResponse(): List<UserContactDataResponse> =
    this.map {
        with(it) {
            UserContactDataResponse(
                beginning = beginning,
                duration = duration,
                number = number,
                name = contactName,
                timesQueried = timesQueried,
            )
        }
    }
