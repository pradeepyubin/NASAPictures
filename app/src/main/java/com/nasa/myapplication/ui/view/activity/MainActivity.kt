package com.nasa.myapplication.ui.view.activity

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.nasa.myapplication.R
import com.nasa.myapplication.util.ConnectivityManager
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    @Inject
    lateinit var connectivityManager: ConnectivityManager

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        initObserver()
    }

    private fun initObserver() {
        connectivityManager.isNetworkAvailable.observe(this) { isAvailable ->
           if(!isAvailable) {
               Toast.makeText(this,"Please connect to network",Toast.LENGTH_SHORT).show()
           }
        }
    }

    override fun onStart() {
        connectivityManager.registerConnectionObserver(this)
        super.onStart()
    }

    override fun onDestroy() {
        connectivityManager.unregisterConnectionObserver(this)
        super.onDestroy()
    }
}