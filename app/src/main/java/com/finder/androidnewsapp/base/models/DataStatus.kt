package com.finder.androidnewsapp.base.models

sealed class DataStatus {
    object DataLoading : DataStatus()
    object DataLoaded : DataStatus()
    data class DataError(val exception: Exception?) : DataStatus()
}