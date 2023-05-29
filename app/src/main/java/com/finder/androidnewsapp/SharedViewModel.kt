package com.finder.androidnewsapp

import androidx.lifecycle.viewModelScope
import com.finder.androidnewsapp.articles.data.model.SearchModel
import com.finder.androidnewsapp.base.baseclasses.BaseViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class SharedViewModel : BaseViewModel() {
    private val _isLoading = MutableStateFlow(true)
    val isLoading = _isLoading.asStateFlow()

    private val _search = MutableSharedFlow<SearchModel>()
    val search = _search.asSharedFlow()

    init {
        viewModelScope.launch {
            delay(3000)
            _isLoading.value = false
        }
    }

    fun sendSearchEvent(searchModel: SearchModel) {
        viewModelScope.launch {
            _search.emit(searchModel)
        }
    }
}