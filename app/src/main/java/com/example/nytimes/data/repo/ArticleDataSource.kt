package com.example.nytimes.data.repo

import com.example.nytimes.data.model.ArticleResponse

interface ArticleDataSource {

    suspend fun getArticlesResponse(section : String ,period: Int,apiKey: String): ArticleResponse
}