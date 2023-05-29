package com.finder.androidnewsapp.articles.data.remote

import com.finder.androidnewsapp.articles.data.model.ArticleModel

interface NewsDataSource {
    suspend fun getNews(
        search: String
    ): List<ArticleModel>
}