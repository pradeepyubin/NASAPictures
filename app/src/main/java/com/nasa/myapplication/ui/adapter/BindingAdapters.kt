package com.nasa.myapplication.ui.adapter

import android.widget.ImageView
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide

@BindingAdapter("picture_url")
fun pictureUrl(imageView: ImageView, url: String) =
    Glide.with(imageView)
        .load(url)
        .into(imageView)