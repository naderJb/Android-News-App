package com.finder.androidnewsapp.base.extensions

import android.widget.ImageView
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide
import com.finder.androidnewsapp.R

@BindingAdapter("loadImage")
fun ImageView.loadImage(url: String?) {
    Glide
        .with(this)
        .load(url)
        .placeholder(R.drawable.ic_star)
        .error(R.drawable.ic_star)
        .fallback(R.drawable.ic_star)
        .into(this)
}