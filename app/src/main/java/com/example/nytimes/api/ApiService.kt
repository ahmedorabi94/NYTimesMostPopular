package com.example.nytimes.api

import com.example.nytimes.data.model.ArticleResponse
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface ApiService {

    @GET("svc/mostpopular/v2/mostviewed/{section}/{period}.json")
    suspend fun getArticlesResponseAsync(
        @Path("section") allSections: String,
        @Path("period") period: Int,
        @Query("api-key") apiKey: String
    ): ArticleResponse
}