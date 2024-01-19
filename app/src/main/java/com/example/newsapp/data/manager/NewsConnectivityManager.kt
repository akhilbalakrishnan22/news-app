/*
 * This file is part of NewsApp application.
 * It defines the connection state and connectivity managers which handle network status.
 */
package com.example.newsapp.data.manager

import android.content.Context
import android.net.ConnectivityManager
import android.net.Network
import android.net.NetworkCapabilities
import android.net.NetworkRequest
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

/**
 * Sealed interface defining different states of network connectivity.
 */
sealed interface ConnectionState {
    data object Connected : ConnectionState
    data object NoInternet : ConnectionState
    data object Unknown : ConnectionState
}

/**
 * The NewsConnectivityManager class manages and monitors the network connection state using Android's ConnectivityManager.
 * It provides updates to clients through a MutableStateFlow.
 *
 * @property context The Android application context used for obtaining system services.
 */
class NewsConnectivityManager(
    context: Context
) {

    /**
     * MutableStateFlow representing the current network connection state.
     * Observers can receive updates about the state changes through this flow.
     */
    private val _connectionState = MutableStateFlow<ConnectionState>(ConnectionState.Unknown)
    val connectionState = _connectionState.asStateFlow()

    /**
     * NetworkCallback implementation to handle network events and update the connection state accordingly.
     */
    private val networkCallback = object : ConnectivityManager.NetworkCallback() {
        override fun onAvailable(network: Network) {
            super.onAvailable(network)
            // Update connection state to Connected when the network is available.
            _connectionState.update {
                ConnectionState.Connected
            }
        }

        override fun onLost(network: Network) {
            super.onLost(network)
            // Update connection state to NoInternet when the network is lost.
            _connectionState.update {
                ConnectionState.NoInternet
            }
        }
    }

    /**
     * ConnectivityManager instance obtained from the provided context.
     */
    private val connectivityManager = context.getSystemService(ConnectivityManager::class.java)

    /**
     * Initialization block where the network callback is registered to receive updates on network changes.
     */
    init {
        connectivityManager.registerNetworkCallback(getNetworkRequest(), networkCallback)
    }

    /**
     * Function to create a NetworkRequest with specified capabilities and transport types.
     *
     * @return A NetworkRequest instance configured with the desired capabilities and transport types.
     */
    private fun getNetworkRequest(): NetworkRequest {
        return NetworkRequest.Builder()
            .addCapability(NetworkCapabilities.NET_CAPABILITY_INTERNET)
            .addTransportType(NetworkCapabilities.TRANSPORT_WIFI)
            .addTransportType(NetworkCapabilities.TRANSPORT_CELLULAR)
            .build()
    }
}