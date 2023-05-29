package com.finder.androidnewsapp.base.extensions

import android.view.View

fun View.visible() = apply { visibility = View.VISIBLE }
fun View.invisible() = apply { visibility = View.INVISIBLE }
fun View.gone() = apply { visibility = View.GONE }