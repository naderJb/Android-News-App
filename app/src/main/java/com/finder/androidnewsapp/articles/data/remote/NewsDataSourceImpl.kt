package com.finder.androidnewsapp.articles.data.remote

import com.finder.androidnewsapp.articles.data.model.ArticleModel
import com.finder.androidnewsapp.articles.data.service.ArticlesService
import com.finder.androidnewsapp.base.extensions.dataOrException
import javax.inject.Inject

class NewsDataSourceImpl @Inject constructor(
    private val articlesService: ArticlesService
) : NewsDataSource {
    override suspend fun getNews(search: String): List<ArticleModel> = try {
        val response = articlesService.getNews(search)
        response.dataOrException("cannot get articles").articles
    } catch (e: Exception) {
        throw e
    }
}