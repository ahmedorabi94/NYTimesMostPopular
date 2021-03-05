package com.example.nytimes.ui.adapter

import com.example.nytimes.data.model.Result

interface ArticleCallback {

    fun onArticleClick(item: Result)
}