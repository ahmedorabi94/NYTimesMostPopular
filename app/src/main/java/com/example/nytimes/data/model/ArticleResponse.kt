package com.example.nytimes.data.model

data class ArticleResponse(
    val copyright: String,
    val num_results: Int,
    val results: List<Result>,
    val status: String
)