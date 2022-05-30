package com.nasa.myapplication.util

import android.content.Context
import android.content.Context.CONNECTIVITY_SERVICE
import android.net.ConnectivityManager
import android.net.Network
import android.net.NetworkCapabilities.NET_CAPABILITY_INTERNET
import android.net.NetworkRequest
import androidx.lifecycle.LiveData


class NetworkListener(context: Context) : LiveData<Boolean>() {

  private lateinit var callback: ConnectivityManager.NetworkCallback
  private val connectivityManager = context.getSystemService(CONNECTIVITY_SERVICE) as ConnectivityManager
  private val networks: MutableSet<Network> = HashSet()

  private fun validate() {
    postValue(networks.size > 0)
  }

  override fun onActive() {
    callback = createNetworkCallback()
    val networkRequest = NetworkRequest.Builder()
      .addCapability(NET_CAPABILITY_INTERNET)
      .build()
    connectivityManager.registerNetworkCallback(networkRequest, callback)
  }

  override fun onInactive() {
    connectivityManager.unregisterNetworkCallback(callback)
  }

  private fun createNetworkCallback() = object : ConnectivityManager.NetworkCallback() {

    override fun onAvailable(network: Network) {
      networks.add(network)
      validate()
    }
    override fun onLost(network: Network) {
      networks.remove(network)
      validate()
    }
  }
}