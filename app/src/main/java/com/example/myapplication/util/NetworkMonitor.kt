package com.example.myapplication.util

import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkRequest
import android.os.Build

class NetworkMonitor(context: Context) {

    private val connectivityManager =
        context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager

    //This function checks whether the device is connected to the internet or not. It returns a boolean value.
    fun isNetworkAvailable(): Boolean {
        val networkInfo = connectivityManager.activeNetworkInfo
        return networkInfo != null && networkInfo.isConnected
    }

    //This function registers a NetworkCallback object to listen for network state changes. It takes a NetworkCallback object as a parameter.
    fun registerNetworkCallback(networkCallback: ConnectivityManager.NetworkCallback) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            connectivityManager.registerDefaultNetworkCallback(networkCallback)
        } else {
            val builder = NetworkRequest.Builder()
            connectivityManager.registerNetworkCallback(builder.build(), networkCallback)
        }
    }


    //This function unregisters a previously registered NetworkCallback object. It takes a NetworkCallback object as a parameter.
    fun unregisterNetworkCallback(networkCallback: ConnectivityManager.NetworkCallback) {
        connectivityManager.unregisterNetworkCallback(networkCallback)
    }
}