package com.example.nytimes.data.repo

import com.example.nytimes.data.api.ApiService
import javax.inject.Inject

class HomeFragmentRepo @Inject constructor(private val apiService: ApiService) : ArticleDataSource {


    override suspend fun getArticlesResponse(section: String, period: Int, apiKey: String) =
        apiService.getArticlesResponseAsync(section, period, apiKey)


}