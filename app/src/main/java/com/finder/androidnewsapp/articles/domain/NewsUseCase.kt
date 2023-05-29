package com.finder.androidnewsapp.articles.domain

import com.finder.androidnewsapp.articles.data.model.ArticleModel
import com.finder.androidnewsapp.articles.data.model.SearchModel
import com.finder.androidnewsapp.base.models.APIResponse
import kotlinx.coroutines.flow.Flow

interface NewsUseCase {

    suspend fun getNews(
        search: String
    ): Flow<APIResponse<List<ArticleModel>>>

    suspend fun getNewsByField(
        searchModel: SearchModel
    ): Flow<APIResponse<List<ArticleModel>>>
}