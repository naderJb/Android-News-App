package com.finder.androidnewsapp.articles.data.local.helpers

import androidx.room.TypeConverter
import com.finder.androidnewsapp.articles.data.model.SourceModel
import com.google.gson.Gson

class Converter {

    private var gson: Gson = Gson()

    @TypeConverter
    fun toSourceModel(sourceModel: String): SourceModel? = try {
        gson.fromJson(sourceModel, SourceModel::class.java)
    } catch (_: Exception) {
        null
    }

    @TypeConverter
    fun fromAddress(sourceModel: SourceModel): String = gson.toJson(sourceModel)
}