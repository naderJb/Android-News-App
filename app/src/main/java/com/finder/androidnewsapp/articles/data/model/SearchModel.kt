package com.finder.androidnewsapp.articles.data.model

data class SearchModel(
    var type: SearchType,
    var searchQuery: String,
)

enum class SearchType(val type: String) {
    TITLE("title"),
    DESCRIPTION("description"),
    AUTHOR("author"),
    CONTENT("content")
}
