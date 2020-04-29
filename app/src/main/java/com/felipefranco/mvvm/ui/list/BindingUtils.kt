package com.felipefranco.mvvm.ui.list

import android.widget.ImageView
import android.widget.TextView
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide
import com.felipefranco.mvvm.domain.ArtistDomain

@BindingAdapter("artistName")
fun TextView.artistName(item: ArtistDomain) {
    item?.let {
        text = it.name
    }
}

@BindingAdapter("artistListeners")
fun TextView.artistListeners(item: ArtistDomain) {
    item?.let {
        text = it.listeners
    }
}

@BindingAdapter("imageUrl")
fun bindImage(image: ImageView, item: ArtistDomain) {
    Glide.with(image.context)
        .load(item.image)
        .into(image)
}