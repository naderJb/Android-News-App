package com.finder.androidnewsapp.base.models


sealed class APIResponse<T>(
    val status: Status,
    var data: T? = null,
    val exception: Exception? = null
) {

    class Success<T>(data: T) : APIResponse<T>(Status.SUCCESS, data)
    class Error<T>(exception: Exception?, data: T? = null) :
        APIResponse<T>(Status.ERROR, data, exception)

    class Loading<T> : APIResponse<T>(Status.LOADING)
}