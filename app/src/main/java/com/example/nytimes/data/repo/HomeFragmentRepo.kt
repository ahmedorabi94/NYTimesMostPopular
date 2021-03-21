package com.example.nytimes.data.repo

import com.example.nytimes.data.api.ApiService
import com.example.nytimes.data.api.ResultWrapper
import com.example.nytimes.data.api.safeApiCall
import com.example.nytimes.data.db.ArticleDao
import com.example.nytimes.data.model.ArticleResponse
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject

class HomeFragmentRepo @Inject constructor(
    private val apiService: ApiService,
    private val articleDao: ArticleDao
) {

    private val dispatcher: CoroutineDispatcher = Dispatchers.IO


    suspend fun getArticlesResponseFlow(
        section: String,
        period: Int,
        apiKey: String
    ): Flow<ResultWrapper<ArticleResponse>> {


        return flow {


            val apiResponse = safeApiCall( dispatcher) {
                apiService.getArticlesResponseAsyncTwo(section, period, apiKey)
            }


            when (apiResponse) {
                is ResultWrapper.Success -> {
                    articleDao.deleteAllArticles()
                    articleDao.insertArticles(apiResponse.value.results)
                    emit(
                        ResultWrapper.Success(
                            ArticleResponse(
                                "",
                                20,
                                articleDao.getAllArticles(),
                                "OK"
                            )
                        )
                    )

                }
                else -> {
                    emit(apiResponse)
                    
                    emit(
                        ResultWrapper.Success(
                            ArticleResponse(
                                "",
                                20,
                                articleDao.getAllArticles(),
                                "OK"
                            )
                        )
                    )
                }
            }


        }.flowOn(Dispatchers.IO)
    }


}