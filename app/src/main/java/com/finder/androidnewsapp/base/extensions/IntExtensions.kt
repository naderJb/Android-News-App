package com.finder.androidnewsapp.base.extensions

fun Int?.safe(defaultValue: Int = 0) = this ?: defaultValue