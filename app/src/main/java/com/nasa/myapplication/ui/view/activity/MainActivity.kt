package com.nasa.myapplication.ui.view.activity

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.snackbar.Snackbar
import com.nasa.myapplication.R
import com.nasa.myapplication.databinding.ActivityMainBinding
import com.nasa.myapplication.util.ConnectivityManager
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    @Inject
    lateinit var connectivityManager: ConnectivityManager
    lateinit var binding : ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        initObserver()
    }

    private fun initObserver() {
        connectivityManager.isNetworkAvailable.observe(this) { isAvailable ->
           if(!isAvailable) {
               Snackbar.make(binding.root, "Please connect to network", Snackbar.LENGTH_LONG).show()
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