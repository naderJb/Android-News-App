package com.finder.androidnewsapp.articles.data.model


import android.os.Parcelable
import androidx.annotation.Keep
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.finder.androidnewsapp.articles.data.local.helpers.DatabaseConstants.TABLE_ARTICLES
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

@Keep
@Parcelize
@Entity(TABLE_ARTICLES)
data class ArticleModel(
    @PrimaryKey(autoGenerate = true)
    val id: Int,

    @SerializedName("author")
    val author: String?,

    @SerializedName("content")
    val content: String?,

    @SerializedName("description")
    val description: String?,

    @SerializedName("publishedAt")
    val publishedAt: String?,

    @SerializedName("source")
    val source: SourceModel?,

    @SerializedName("title")
    val title: String?,

    @SerializedName("url")
    val url: String?,

    @SerializedName("urlToImage")
    val urlToImage: String?
) : Parcelable