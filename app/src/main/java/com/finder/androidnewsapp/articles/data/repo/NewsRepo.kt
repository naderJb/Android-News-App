package com.finder.androidnewsapp.articles.data.repo

import com.finder.androidnewsapp.articles.data.model.ArticleModel
import com.finder.androidnewsapp.base.models.APIResponse
import kotlinx.coroutines.flow.Flow

interface NewsRepo {
    suspend fun getNews(
        search: String
    ): Flow<APIResponse<List<ArticleModel>>>
}