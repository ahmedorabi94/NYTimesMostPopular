package com.example.nytimes.data.repo

import com.example.nytimes.api.ApiService
import javax.inject.Inject

class HomeFragmentRepo @Inject constructor(private val apiService: ApiService) {


    suspend fun getArticlesResponse() =
        apiService.getArticlesResponseAsync("all-sections", 7, "DM0wSUOy0AbaD4OoYd80zXvFsy5xZKmT")


}