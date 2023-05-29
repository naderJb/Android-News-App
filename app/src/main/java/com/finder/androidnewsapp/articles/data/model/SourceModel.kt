package com.finder.androidnewsapp.articles.data.model


import android.os.Parcelable
import androidx.annotation.Keep
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.finder.androidnewsapp.articles.data.local.helpers.DatabaseConstants.TABLE_SOURCE
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

@Keep
@Parcelize
@Entity(TABLE_SOURCE)
data class SourceModel(
    @PrimaryKey(autoGenerate = true)
    val _id: Int,
    @SerializedName("id")
    val id: String?,
    @SerializedName("name")
    val name: String?
) : Parcelable