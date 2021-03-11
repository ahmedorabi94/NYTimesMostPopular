package com.example.nytimes.data.repo

import com.example.nytimes.data.api.NetworkResponse
import com.example.nytimes.data.model.ArticleResponse
import java.lang.Error

interface ArticleDataSource {

    suspend fun getArticlesResponse(section : String ,period: Int,apiKey: String): NetworkResponse<ArticleResponse,Error>
}