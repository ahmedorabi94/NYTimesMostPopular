package com.example.nytimes.ui.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.nytimes.data.api.Resource
import com.example.nytimes.data.api.ResultWrapper
import com.example.nytimes.data.model.ArticleResponse
import com.example.nytimes.data.repo.HomeFragmentRepo
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.onCompletion
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.launch
import timber.log.Timber
import javax.inject.Inject

class HomeViewModel @Inject constructor(private val repo: HomeFragmentRepo) : ViewModel() {


    private val _articleResponse = MutableLiveData<Resource<ArticleResponse>>()
    val articleResponse: LiveData<Resource<ArticleResponse>> get() = _articleResponse


    init {
        getArticlesFlow()
    }


     fun getArticlesFlow() {
        viewModelScope.launch {
            repo.getArticlesResponseFlow("all-sections", 7, "DM0wSUOy0AbaD4OoYd80zXvFsy5xZKmT")
                .onStart {
                    Timber.e("Start")
                    _articleResponse.value = Resource.loading(data = null)
                }.catch { exception ->
                    Timber.e(exception)
                    _articleResponse.value =
                        Resource.error(data = null, message = "Unknown Error")
                }.onCompletion {
                    Timber.e("onCompletion")
                }.collect { response ->

                    when (response) {
                        is ResultWrapper.Success -> {
                            Timber.e("Success ${response.value}")
                            _articleResponse.value = Resource.success(response.value)
                        }
                        is ResultWrapper.Error -> {
                            val errorResponse = response.error
                            val code = response.code

                            Timber.e("ApiError Code : $code  Message : ${errorResponse?.message}")

                            if (errorResponse != null) {
                                _articleResponse.value =
                                    Resource.error(data = null, message = errorResponse.message)
                            } else {
                                _articleResponse.value =
                                    Resource.error(data = null, message = "Unknown Error")
                            }
                        }
                        is ResultWrapper.NetworkError -> {
                            _articleResponse.value =
                                Resource.error(data = null, message = "NetworkError .")

                        }

                    }


                }
        }

    }


}