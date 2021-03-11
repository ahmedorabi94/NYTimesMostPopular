package com.example.nytimes.data.repo

import com.example.nytimes.data.api.ApiService
import com.example.nytimes.data.api.newapiresponse.ResultWrapper
import com.example.nytimes.data.api.newapiresponse.safeApiCall
import com.example.nytimes.data.model.ArticleResponse
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import javax.inject.Inject

class HomeFragmentRepo @Inject constructor(private val apiService: ApiService) {

    private val dispatcher: CoroutineDispatcher = Dispatchers.IO


    suspend fun getArticlesResponse(section: String, period: Int, apiKey: String) =
        apiService.getArticlesResponseAsync(section, period, apiKey)


    suspend fun getArticlesResponseTwo(section: String, period: Int, apiKey: String) : ResultWrapper<ArticleResponse> {

        return safeApiCall(dispatcher){
            apiService.getArticlesResponseAsyncTwo(section, period, apiKey)
        }
    }



}