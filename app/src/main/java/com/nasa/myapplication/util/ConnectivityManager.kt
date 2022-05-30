package com.nasa.myapplication.util

import android.app.Application
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.MutableLiveData
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class ConnectivityManager @Inject constructor (application: Application) {

  private val connectionLiveData = NetworkListener(application)
  var isNetworkAvailable = MutableLiveData<Boolean>()

  fun registerConnectionObserver(lifecycleOwner: LifecycleOwner){
    connectionLiveData.observe(lifecycleOwner) { isConnected ->
      isConnected?.let { isNetworkAvailable.value = it }
    }
  }

  fun unregisterConnectionObserver(lifecycleOwner: LifecycleOwner){
    connectionLiveData.removeObservers(lifecycleOwner)
  }
}