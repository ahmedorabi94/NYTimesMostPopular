package com.example.nytimes.ui

import com.example.nytimes.data.model.Result

interface ArticleCallback {

    fun onArticleClick(item: Result)
}