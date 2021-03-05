package com.example.nytimes.data.source

import com.example.nytimes.data.model.ArticleResponse
import com.example.nytimes.data.model.Result
import com.example.nytimes.data.repo.ArticleDataSource

class FakeDataSource(var articles: MutableList<Result>? = mutableListOf()) : ArticleDataSource {


    override suspend fun getArticlesResponse(section: String, period: Int, apiKey: String): ArticleResponse {
        articles?.let {
            return ArticleResponse("", 10, it, "Success")
        }

        return ArticleResponse("", 0, emptyList(), "Error")
    }
}