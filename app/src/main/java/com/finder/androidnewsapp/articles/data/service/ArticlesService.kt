package com.finder.androidnewsapp.articles.data.service

import com.finder.androidnewsapp.articles.data.model.ArticleModel
import com.finder.androidnewsapp.articles.data.model.CompaniesEnum
import com.finder.androidnewsapp.base.models.GlobalResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface ArticlesService {

    @GET(GET_EVERYTHING)
    suspend fun getNews(
        @Query("q") search: String = CompaniesEnum.GOOGLE.name
//        @Query("country") country: String = CountiesEnum.US.country
    ): Response<GlobalResponse<List<ArticleModel>>>

    companion object {
        const val GET_EVERYTHING = "everything"
    }
}