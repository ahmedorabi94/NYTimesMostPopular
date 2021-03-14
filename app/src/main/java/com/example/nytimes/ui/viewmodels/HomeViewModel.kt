package com.example.nytimes.ui.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.nytimes.data.api.NetworkResponse
import com.example.nytimes.data.api.Resource
import com.example.nytimes.data.api.newapiresponse.ResultWrapper
import com.example.nytimes.data.model.ArticleResponse
import com.example.nytimes.data.repo.HomeFragmentRepo
import kotlinx.coroutines.launch
import timber.log.Timber
import javax.inject.Inject

class HomeViewModel @Inject constructor(private val repo: HomeFragmentRepo) : ViewModel() {


    private val _articleResponse = MutableLiveData<Resource<ArticleResponse>>()
    val articleResponse: LiveData<Resource<ArticleResponse>> get() = _articleResponse

    fun getArticlesTest(section: String, period: Int, apiKey: String) {


        viewModelScope.launch {

            _articleResponse.value = Resource.loading(data = null)


            when (val response = repo.getArticlesResponse(section, period, apiKey)) {
                is NetworkResponse.Success -> {
                    Timber.e("Success ${response.body}")
                    _articleResponse.value = Resource.success(response.body)
                }
                is NetworkResponse.ApiError -> {
                    val msg = response.message
                    Timber.e(msg)

                    Timber.e("ApiError $msg")
                    _articleResponse.value =
                        Resource.error(data = null, message = msg)
                }
                is NetworkResponse.NetworkError -> {
                    Timber.e("NetworkError ${response.error}")
                    _articleResponse.value =
                        Resource.error(data = null, message = "NetworkError  $response.error")

                }
                is NetworkResponse.UnknownError -> {
                    Timber.e("UnknownError ${response.error}")
                    _articleResponse.value =
                        Resource.error(data = null, message = "UnknownError  $response.error")

                }
            }

        }


    }

    fun getArticles(section: String, period: Int, apiKey: String) {


        viewModelScope.launch {

            _articleResponse.value = Resource.loading(data = null)


            when (val response = repo.getArticlesResponseTwo(section, period, apiKey)) {
                is ResultWrapper.Success -> {
                    Timber.e("Success ${response.value}")
                    _articleResponse.value = Resource.success(response.value)
                }
                is ResultWrapper.Error -> {

                    Timber.e("ApiError")

                    val errorResponse = response.error

                    if (errorResponse != null) {
                        _articleResponse.value =
                            Resource.error(data = null, message = errorResponse.message)
                    } else {
                        _articleResponse.value =
                            Resource.error(data = null, message = "Unknown Error")
                    }
                }
                is ResultWrapper.NetworkError -> {
                    //   Timber.e("NetworkError ${response.error}")
                    _articleResponse.value =
                        Resource.error(data = null, message = "NetworkError .")

                }

            }

        }


    }


}