package com.mk.infrastructure

import android.net.ConnectivityManager
import android.net.Network
import android.net.NetworkCapabilities
import android.net.NetworkRequest
import com.mk.infrastructure.di.IoDispatcher
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Job
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.callbackFlow
import kotlinx.coroutines.flow.stateIn
import javax.inject.Inject

class NetworkStateManager @Inject constructor(
    private val connectivityManager: ConnectivityManager,
    @IoDispatcher private val ioDispatcher: CoroutineDispatcher,
) {
    private val scope = CoroutineScope(ioDispatcher + Job())

    private fun networkCallback(callback: (NetworkState) -> Unit): ConnectivityManager.NetworkCallback {
        return object : ConnectivityManager.NetworkCallback() {
            override fun onAvailable(network: Network) {
                callback(NetworkState.AVAILABLE)
            }

            override fun onLost(network: Network) {
                callback(NetworkState.UNAVAILABLE)
            }
        }
    }

    private fun getCurrentConnectivityState(connectivityManager: ConnectivityManager): NetworkState {
        val network = connectivityManager.activeNetwork

        val connected = connectivityManager.getNetworkCapabilities(network)
            ?.hasCapability(NetworkCapabilities.NET_CAPABILITY_INTERNET) ?: false

        return if (connected) NetworkState.AVAILABLE else NetworkState.UNAVAILABLE
    }

    fun observeConnectivityAsFlow(): StateFlow<NetworkState> = callbackFlow {
        val callback = networkCallback { connectionState ->
            trySend(connectionState)
        }

        val networkRequest = NetworkRequest.Builder()
            .addCapability(NetworkCapabilities.NET_CAPABILITY_INTERNET)
            .build()

        connectivityManager.registerNetworkCallback(networkRequest, callback)

        val currentState = getCurrentConnectivityState(connectivityManager)
        trySend(currentState)

        awaitClose {
            connectivityManager.unregisterNetworkCallback(callback)
        }
    }.stateIn(scope, SharingStarted.Eagerly, NetworkState.IDLE)
}
