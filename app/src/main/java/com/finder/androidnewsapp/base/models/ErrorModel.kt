package com.finder.androidnewsapp.base.models


import androidx.annotation.Keep
import com.google.gson.annotations.SerializedName

@Keep
data class ErrorModel(
    @SerializedName("code")
    val code: String?,
    @SerializedName("message")
    val message: String?,
    @SerializedName("status")
    val status: String?
)