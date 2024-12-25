package com.mk.networking

import callLogs.ContactLoggerManager
import com.mk.infrastructure.AppSessionManager
import com.mk.infrastructure.NetworkState
import com.mk.infrastructure.models.UserContactData
import com.mk.infrastructure.phoneSession.PhoneCallSession
import com.mk.infrastructure.phoneSession.PhoneCallSessionManager
import com.mk.networking.NetworkUtils.getRouteList
import com.mk.networking.response.Service
import com.mk.networking.response.createDirectoryResponse
import com.mk.networking.response.createServiceList
import com.mk.networking.response.toDataResponse
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

/**
 * A server that provides endpoints for monitoring and interacting with the application's state.
 *
 * This server uses Ktor's embedded server to expose information about the current phone call session,
 * logged contact data and the available endpoints.
 *
 * @property phoneCallSessionManager Manages the state of the current phone call session.
 * @property contactLoggerManager Manages logging of phone call events.
 */
class AssessmentServer @Inject constructor() {
    @Inject lateinit var phoneCallSessionManager: PhoneCallSessionManager

    @Inject lateinit var contactLoggerManager: ContactLoggerManager

    @Inject lateinit var appSessionManager: AppSessionManager

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
                    call.respond(
                        phoneCallSessionManager.phoneCallState.value,
                        typeInfo<PhoneCallSession>(),
                    )
                }
                get("/log") {
                    call.response.status(HttpStatusCode.OK)
                    call.respond(
                        contactLoggerManager.getSavedLaunchCallLogs().toDataResponse(),
                        typeInfo<UserContactData>(),
                    )
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

    fun resetServer() {
        server.reload()
    }

    fun start(callback: (isSuccess: Boolean) -> Unit) {
        ioScope.launch {
            if (appSessionManager.networkState.value != NetworkState.AVAILABLE) {
                callback(false)
                return@launch
            }
            try {
                server.start(wait = true)
            } catch (e: Exception) {
                e.printStackTrace()
                callback(false)
            }
        }
    }

    fun stop(callback: (isSuccess: Boolean) -> Unit) {
        try {
            server.stop(1_000, 2_000)
        } catch (e: Exception) {
            e.printStackTrace()
            callback(false)
        }
    }

    val host: String
        get() = String.format(Locale.ENGLISH, "%s:%d", NetworkUtils.getLocalIpAddress(), PORT)
}
