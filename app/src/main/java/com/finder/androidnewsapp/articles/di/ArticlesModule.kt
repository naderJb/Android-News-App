package com.finder.androidnewsapp.articles.di

import com.finder.androidnewsapp.articles.data.remote.NewsDataSource
import com.finder.androidnewsapp.articles.data.remote.NewsDataSourceImpl
import com.finder.androidnewsapp.articles.data.repo.NewsRepo
import com.finder.androidnewsapp.articles.data.repo.NewsRepoImpl
import com.finder.androidnewsapp.articles.data.service.ArticlesService
import com.finder.androidnewsapp.articles.domain.NewsUseCase
import com.finder.androidnewsapp.articles.domain.NewsUseCaseImpl
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class ArticlesModule {
    @Singleton
    @Provides
    fun provideArticlesService(
        retrofit: Retrofit
    ): ArticlesService = retrofit.create(ArticlesService::class.java)


    @Module
    @InstallIn(SingletonComponent::class)
    abstract class ArticlesBinder() {
        @Binds
        abstract fun bindNewsDataSource(dataSource: NewsDataSourceImpl): NewsDataSource

        @Binds
        abstract fun bindNewsRepo(repo: NewsRepoImpl): NewsRepo

        @Binds
        abstract fun bindNewsUseCase(useCase: NewsUseCaseImpl): NewsUseCase

    }
}