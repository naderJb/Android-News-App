package com.finder.androidnewsapp.base.models

import androidx.annotation.Keep
import com.google.gson.annotations.SerializedName

@Keep
data class GlobalResponse<T>(
    @SerializedName("status")
    val status: String?,
    @SerializedName("totalResults")
    val totalResults: Int?,
    @SerializedName("articles")
    val articles: T,
)
