package com.finder.androidnewsapp.articles.data.local

import androidx.room.Dao
import androidx.room.Query
import androidx.room.Upsert
import com.finder.androidnewsapp.articles.data.local.helpers.DatabaseConstants
import com.finder.androidnewsapp.articles.data.model.ArticleModel

@Dao
interface ArticleDao {
    @Upsert
    suspend fun upsertAll(articles: List<ArticleModel>)

    @Query("SELECT * FROM ${DatabaseConstants.TABLE_ARTICLES}")
    fun getAllArticles(): List<ArticleModel>

    @Query("DELETE FROM  ${DatabaseConstants.TABLE_ARTICLES}")
    suspend fun clearAll()

    @Query("SELECT * FROM ${DatabaseConstants.TABLE_ARTICLES} WHERE LOWER(title) LIKE '%' || LOWER(:search) ||'%' ")
    suspend fun getArticlesByTitle(search: String): List<ArticleModel>

    @Query("SELECT * FROM ${DatabaseConstants.TABLE_ARTICLES} WHERE LOWER(description) LIKE '%' || LOWER(:search) ||'%' ")
    suspend fun getArticlesByDescription(search: String): List<ArticleModel>

    @Query("SELECT * FROM ${DatabaseConstants.TABLE_ARTICLES} WHERE LOWER(author) LIKE '%' || LOWER(:search) ||'%' ")
    suspend fun getArticlesByAuthor(search: String): List<ArticleModel>

    @Query("SELECT * FROM ${DatabaseConstants.TABLE_ARTICLES} WHERE LOWER(content) LIKE '%' || LOWER(:search) ||'%' ")
    suspend fun getArticlesByContent(search: String): List<ArticleModel>
}