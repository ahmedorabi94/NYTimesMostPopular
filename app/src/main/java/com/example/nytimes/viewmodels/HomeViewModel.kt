package com.example.nytimes.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.liveData
import com.example.nytimes.api.Resource
import com.example.nytimes.data.repo.HomeFragmentRepo
import kotlinx.coroutines.Dispatchers
import javax.inject.Inject

class HomeViewModel @Inject constructor(private val repo: HomeFragmentRepo) : ViewModel() {


    fun getArticles() = liveData(Dispatchers.IO) {

        emit(Resource.loading(data = null))
        try {
            emit(Resource.success(data = repo.getArticlesResponse()))
        } catch (exception: Exception) {
            emit(Resource.error(data = null, message = exception.message ?: "Error Occurred!"))
        }

    }
}