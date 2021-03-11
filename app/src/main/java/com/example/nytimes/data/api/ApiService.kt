package com.example.nytimes.data.api

import com.example.nytimes.data.model.ArticleResponse
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface ApiService {


    // Coroutines managing background threads that can simplify code by reducing the need for callbacks.
    // Kotlin coroutines let you convert callback-based code to sequential code.
    // Code written sequentially is typically easier to read, and can even use language features such as exceptions.

    //The keyword suspend is Kotlin's way of marking a function, or function type, available to coroutines.
    // it suspends execution until the result is ready then it resumes where it left off with the result.
    // While it's suspended waiting for a result, it unblocks the thread that it's running on so other functions or coroutines can run.

    //A scope controls the lifetime of coroutines through its job
    //  A dispatcher controls which thread runs a coroutine.

    // To switch between any dispatcher, coroutines uses withContext

    @GET("svc/mostpopular/v2/mostviewed/{section}/{period}.json")
    suspend fun getArticlesResponseAsync(
        @Path("section") allSections: String,
        @Path("period") period: Int,
        @Query("api-key") apiKey: String
    ): NetworkResponse<ArticleResponse, ErrorApi>


}