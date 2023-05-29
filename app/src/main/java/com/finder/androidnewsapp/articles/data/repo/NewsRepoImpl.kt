package com.finder.androidnewsapp.articles.data.repo

import com.finder.androidnewsapp.articles.data.model.ArticleModel
import com.finder.androidnewsapp.articles.data.remote.NewsDataSource
import com.finder.androidnewsapp.base.models.APIResponse
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class NewsRepoImpl @Inject constructor(
    private val newsDataSource: NewsDataSource
) : NewsRepo {
    override suspend fun getNews(
        search: String
    ): Flow<APIResponse<List<ArticleModel>>> = flow {
        emit(APIResponse.Loading())
        try {
            val response = newsDataSource.getNews(search)
            emit(APIResponse.Success(response))
        } catch (e: Exception) {
            emit(APIResponse.Error(e))
        }
    }
}