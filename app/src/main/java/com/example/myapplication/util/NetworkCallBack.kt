package com.example.myapplication.util

import android.net.ConnectivityManager
import android.net.Network

object NetworkCallBack : ConnectivityManager.NetworkCallback(){
    override fun onAvailable(network: Network) {
        super.onAvailable(network)
    }

    override fun onLost(network: Network) {
        super.onLost(network)
    }

}