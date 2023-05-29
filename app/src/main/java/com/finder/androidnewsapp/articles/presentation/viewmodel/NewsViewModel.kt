package com.finder.androidnewsapp.articles.presentation.viewmodel

import androidx.lifecycle.viewModelScope
import com.finder.androidnewsapp.articles.data.model.ArticleModel
import com.finder.androidnewsapp.articles.data.model.SearchModel
import com.finder.androidnewsapp.articles.domain.NewsUseCase
import com.finder.androidnewsapp.base.baseclasses.BaseViewModel
import com.finder.androidnewsapp.base.models.DataStatus
import com.finder.androidnewsapp.base.models.Status
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class NewsViewModel @Inject constructor(
    private val newsUseCase: NewsUseCase
) : BaseViewModel() {

    private val _news: MutableStateFlow<List<ArticleModel>> = MutableStateFlow(listOf())
    val news = _news.asStateFlow()

    private val _status: MutableStateFlow<DataStatus> = MutableStateFlow(DataStatus.DataLoaded)
    val status = _status.asStateFlow()


    fun getNews(
        search: String
    ) {
        viewModelScope.launch {
            newsUseCase.getNews(search).collect { dataStatus ->
                when (dataStatus.status) {
                    Status.LOADING -> _status.emit(DataStatus.DataLoading)

                    Status.SUCCESS -> {
                        _status.emit(DataStatus.DataLoaded)
                        dataStatus.data?.let {
                            _news.emit(it)
                        }
                    }

                    Status.ERROR -> {
                        _status.emit(DataStatus.DataError(dataStatus.exception))
                        dataStatus.data?.let {
                            _news.emit(it)
                        }
                    }
                }
            }
        }
    }

    fun getNewsByField(
        searchModel: SearchModel
    ) {
        viewModelScope.launch {
            newsUseCase.getNewsByField(searchModel).collect { dataStatus ->
                when (dataStatus.status) {
                    Status.LOADING -> _status.emit(DataStatus.DataLoading)

                    Status.SUCCESS -> {
                        _status.emit(DataStatus.DataLoaded)
                        dataStatus.data?.let {
                            _news.emit(it)
                        }
                    }

                    Status.ERROR -> {
                        _status.emit(DataStatus.DataError(dataStatus.exception))
                        dataStatus.data?.let {
                            _news.emit(it)
                        }
                    }
                }
            }
        }
    }
}