package com.example.nytimes.ui.viewmodels

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.liveData
import com.example.nytimes.data.api.Resource
import com.example.nytimes.data.model.ArticleResponse
import com.example.nytimes.data.repo.HomeFragmentRepo
import kotlinx.coroutines.Dispatchers
import javax.inject.Inject

class HomeViewModel @Inject constructor(private val repo: HomeFragmentRepo) : ViewModel() {

     val articleResponse = MutableLiveData<Resource<ArticleResponse>>()

    fun getArticles(section : String ,period: Int,apiKey: String) = liveData(Dispatchers.IO) {


        emit(Resource.loading(data = null))
        try {
            emit(Resource.success(data = repo.getArticlesResponse(section,period,apiKey)))
         //   articleResponse.value = Resource.success(data = repo.getArticlesResponse(section,period,apiKey))
        } catch (exception: Exception) {
            emit(Resource.error(data = null, message = exception.message ?: "Error Occurred!"))
        }

    }



}