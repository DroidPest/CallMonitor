package com.mk.networking.response

import com.mk.infrastructure.getCurrentTime

fun createDirectoryResponse(services: List<Service>): DirectoryResponse =
    DirectoryResponse(
        start = getCurrentTime(),
        services = services,
    )

fun createServiceList(host: String, endpoints: List<String>): List<Service> =
    endpoints.map {
        Service(
            name = it.substringAfter("/"),
            uri = host + it,
        )
    }

data class DirectoryResponse(
    val start: String,
    val services: List<Service>,
)

data class Service(
    val name: String,
    val uri: String,
)
