package com.example.nytimes.data.model

data class ArticleResponse(
    val copyright: String,
    val num_results: Int,
    val results: List<Article>,
    val status: String
)