package com.finder.androidnewsapp.base.database

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.finder.androidnewsapp.articles.data.local.ArticleDao
import com.finder.androidnewsapp.articles.data.local.helpers.Converter
import com.finder.androidnewsapp.articles.data.local.helpers.DatabaseConstants
import com.finder.androidnewsapp.articles.data.model.ArticleModel
import com.finder.androidnewsapp.articles.data.model.SourceModel

@Database(
    entities = [ArticleModel::class, SourceModel::class],
    version = DatabaseConstants.DATABASE_VERSION
)
@TypeConverters(Converter::class)
abstract class NewsDatabase : RoomDatabase() {
    abstract fun getArticleDao(): ArticleDao
}