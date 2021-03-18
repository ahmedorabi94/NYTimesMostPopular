package com.example.nytimes.ui.adapter

import com.example.nytimes.data.model.Article

interface ArticleCallback {

    fun onArticleClick(item: Article)
}