package com.example.nytimes.ui.viewmodels

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.nytimes.data.api.NetworkResponse
import com.example.nytimes.data.api.Resource
import com.example.nytimes.data.model.ArticleResponse
import com.example.nytimes.data.repo.HomeFragmentRepo
import kotlinx.coroutines.launch
import timber.log.Timber
import javax.inject.Inject

class HomeViewModel @Inject constructor(private val repo: HomeFragmentRepo) : ViewModel() {


    val articleResponse = MutableLiveData<Resource<ArticleResponse>>()


    fun getArticlesTest(section: String, period: Int, apiKey: String) {


        viewModelScope.launch {

            articleResponse.value = Resource.loading(data = null)


            when (val response = repo.getArticlesResponse(section, period, apiKey)) {
                is NetworkResponse.Success -> {
                    Timber.e("Success ${response.body}")
                    articleResponse.value = Resource.success(response.body)
                }
                is NetworkResponse.ApiError -> {
                    val msg = response.message
                    Timber.e(msg)

                    Timber.e("ApiError $msg")
                    articleResponse.value =
                        Resource.error(data = null, message = msg)
                }
                is NetworkResponse.NetworkError -> {
                    Timber.e("NetworkError ${response.error}")
                    articleResponse.value =
                        Resource.error(data = null, message = "NetworkError  $response.error")

                }
                is NetworkResponse.UnknownError -> {
                    Timber.e("UnknownError ${response.error}")
                    articleResponse.value =
                        Resource.error(data = null, message = "UnknownError  $response.error")

                }
            }

        }


    }


}