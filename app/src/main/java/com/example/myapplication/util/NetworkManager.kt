package com.example.myapplication.util

import android.content.Context
import android.net.ConnectivityManager
import android.net.Network
import android.net.NetworkCapabilities
import android.net.NetworkRequest
import androidx.lifecycle.LiveData

class NetworkManager(private val context: Context):LiveData<Boolean>() {

    override fun onActive() {
        super.onActive()
        checkNetworkConnectivity()
    }

    override fun onInactive() {
        super.onInactive()

        connectivityManager.unregisterNetworkCallback(networkCallBack)
    }

    private var connectivityManager = context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager

    private val networkCallBack = object : ConnectivityManager.NetworkCallback(){
        override fun onAvailable(network: Network) {
            super.onAvailable(network)
            postValue(true)
        }

        override fun onLosing(network: Network, maxMsToLive: Int) {
            super.onLosing(network, maxMsToLive)
            postValue(false)
        }

        override fun onLost(network: Network) {
            super.onLost(network)
            postValue(false)
        }

        override fun onUnavailable() {
            super.onUnavailable()
            postValue(false)
        }
    }

    private fun checkNetworkConnectivity (){
        val network = connectivityManager.activeNetwork
        if (network == null){
            postValue(false)

            val requestBuilder = NetworkRequest.Builder().apply {
                addCapability(NetworkCapabilities.NET_CAPABILITY_VALIDATED)
                addCapability(NetworkCapabilities.NET_CAPABILITY_INTERNET)
                addTransportType(NetworkCapabilities.TRANSPORT_CELLULAR)
                addTransportType(NetworkCapabilities.TRANSPORT_WIFI)
                addTransportType(NetworkCapabilities.TRANSPORT_ETHERNET)
            }.build()

            connectivityManager.registerNetworkCallback(requestBuilder,networkCallBack)
        }
    }

}