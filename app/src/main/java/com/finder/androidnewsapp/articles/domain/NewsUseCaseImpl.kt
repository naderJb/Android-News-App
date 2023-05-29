package com.finder.androidnewsapp.articles.domain

import com.finder.androidnewsapp.articles.data.model.ArticleModel
import com.finder.androidnewsapp.articles.data.model.SearchModel
import com.finder.androidnewsapp.articles.data.model.SearchType
import com.finder.androidnewsapp.articles.data.repo.NewsRepo
import com.finder.androidnewsapp.base.database.NewsDatabase
import com.finder.androidnewsapp.base.exceptions.NoInternetException
import com.finder.androidnewsapp.base.extensions.safe
import com.finder.androidnewsapp.base.models.APIResponse
import com.finder.androidnewsapp.base.models.Status
import com.finder.androidnewsapp.base.utils.GlobalFunctions
import com.finder.androidnewsapp.base.utils.Prefs
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.map
import java.time.LocalDateTime
import javax.inject.Inject

class NewsUseCaseImpl @Inject constructor(
    private val newsRepo: NewsRepo, private val newsDatabase: NewsDatabase, private val prefs: Prefs
) : NewsUseCase {
    override suspend fun getNews(
        search: String
    ): Flow<APIResponse<List<ArticleModel>>> {
        val result = if (GlobalFunctions.hasOneDayPassed(prefs.lastSync.safe())) {
            prefs.lastSync = LocalDateTime.now().toString()
            newsRepo.getNews(search).map { apiResponse ->
                if (apiResponse.status == Status.ERROR && apiResponse.exception is NoInternetException) {
                    apiResponse.apply { data = newsDatabase.getArticleDao().getAllArticles() }
                }
                apiResponse.data?.let { newsDatabase.getArticleDao().upsertAll(it) }
                apiResponse
            }
        } else {
            flow {
                emit(APIResponse.Loading())
                emit(APIResponse.Success(newsDatabase.getArticleDao().getAllArticles()))
            }
        }
        return result
    }

    override suspend fun getNewsByField(searchModel: SearchModel): Flow<APIResponse<List<ArticleModel>>> =
        flow {
            emit(APIResponse.Loading())
            emit(
                APIResponse.Success(
                    when (searchModel.type) {
                        SearchType.TITLE -> newsDatabase.getArticleDao()
                            .getArticlesByTitle(searchModel.searchQuery)

                        SearchType.DESCRIPTION -> newsDatabase.getArticleDao()
                            .getArticlesByDescription(searchModel.searchQuery)

                        SearchType.AUTHOR -> newsDatabase.getArticleDao()
                            .getArticlesByAuthor(searchModel.searchQuery)

                        SearchType.CONTENT -> newsDatabase.getArticleDao()
                            .getArticlesByContent(searchModel.searchQuery)
                    }
                )
            )
        }

}