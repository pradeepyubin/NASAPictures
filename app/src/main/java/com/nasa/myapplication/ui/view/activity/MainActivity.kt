package com.nasa.myapplication.ui.view.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.nasa.myapplication.R

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }
}