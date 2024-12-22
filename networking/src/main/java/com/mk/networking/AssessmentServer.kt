package com.mk.networking

import callLogs.ContactLoggerManager
import com.mk.infrastructure.phoneSession.PhoneCallSession
import com.mk.infrastructure.phoneSession.PhoneCallSessionManager
import com.mk.networking.NetworkUtils.getRouteList
import com.mk.networking.response.Service
import com.mk.networking.response.createDirectoryResponse
import com.mk.networking.response.createServiceList
import io.ktor.http.HttpStatusCode
import io.ktor.serialization.gson.gson
import io.ktor.server.application.install
import io.ktor.server.engine.embeddedServer
import io.ktor.server.netty.Netty
import io.ktor.server.plugins.contentnegotiation.ContentNegotiation
import io.ktor.server.routing.get
import io.ktor.server.routing.routing
import io.ktor.util.reflect.typeInfo
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.util.Locale
import javax.inject.Inject

class AssessmentServer @Inject constructor() {
    @Inject lateinit var phoneCallSessionManager: PhoneCallSessionManager

    @Inject lateinit var contactLoggerManager: ContactLoggerManager

    companion object {
        private const val PORT = 12345
        private val ioScope = CoroutineScope(Dispatchers.IO)
    }

    private val server by lazy {
        embeddedServer(Netty, PORT) {
            val routes = mutableListOf<Service>()

            install(ContentNegotiation) {
                gson {
                    setPrettyPrinting()
                    disableHtmlEscaping()
                }
            }

            routing {
                get("/") {
                    call.response.status(HttpStatusCode.OK)
                    call.respond(createDirectoryResponse(routes), typeInfo<Service>())
                }
                get("/status") {
                    call.response.status(HttpStatusCode.OK)
                    call.respond(phoneCallSessionManager.phoneCallState.value, typeInfo<PhoneCallSession>())
                }
                get("/log") {
                    call.response.status(HttpStatusCode.OK)
                    call.respond(contactLoggerManager.savedLaunchCallLogs.value, typeInfo<PhoneCallSession>())
                }
            }.run {
                routes.addAll(
                    createServiceList(
                        host = NetworkUtils.getLocalIpAddress().toString(),
                        endpoints = getRouteList(children, 1),
                    ),
                )
            }
        }
    }

    fun start() {
        ioScope.launch {
            try {
                server.start(wait = true)
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
    }

    fun stop() {
        try {
            server.stop(1_000, 2_000)
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }

    val host: String
        get() = String.format(Locale.ENGLISH, "%s:%d", NetworkUtils.getLocalIpAddress(), PORT)
}
