package com.nasa.myapplication.ui.adapter

import android.widget.ImageView
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide
import com.nasa.myapplication.R

@BindingAdapter("picture_url")
fun pictureUrl(imageView: ImageView, url: String) =
    Glide.with(imageView)
        .load(url)
        .placeholder(R.drawable.default_image)
        .into(imageView)