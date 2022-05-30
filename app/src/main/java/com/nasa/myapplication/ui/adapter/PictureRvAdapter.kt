package com.nasa.myapplication.ui.adapter

import android.content.Context
import android.graphics.Bitmap
import android.graphics.drawable.Drawable
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.palette.graphics.Palette
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.target.CustomTarget
import com.bumptech.glide.request.transition.Transition
import com.nasa.myapplication.data.Picture
import com.nasa.myapplication.databinding.PictureRowBinding

class PictureRvAdapter (private val items: List<Picture>, private val context: Context) :
    RecyclerView.Adapter<PictureRvAdapter.ViewHolder>() {

    inner class ViewHolder(private val binding: PictureRowBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(album: Picture) {
            binding.picture = album
            setCustomisedBgColor(binding,album)
            binding.albumCard.transitionName = album.service_version
        }
    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
        ViewHolder(
            PictureRowBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )

    override fun getItemCount() = items.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) = holder.bind(items[position])

    fun setCustomisedBgColor(binding: PictureRowBinding, album: Picture) {
        Glide.with(context)
            .asBitmap()
            .load(album.url)
            .into(object : CustomTarget<Bitmap>() {
                override fun onResourceReady(
                    resource: Bitmap,
                    transition: Transition<in Bitmap>?
                ) {
                    val palette = Palette.from(resource).generate()
                    palette.darkVibrantSwatch?.let {
                        binding.albumCard.setCardBackgroundColor(it.rgb)
                    } ?: palette.lightVibrantSwatch?.let {
                        binding.albumCard.setCardBackgroundColor(it.rgb)
                    }
                }

                override fun onLoadCleared(placeholder: Drawable?) {}
            })
    }
}
