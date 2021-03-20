package com.example.nytimes.data.repo

import com.example.nytimes.data.api.ApiService
import com.example.nytimes.data.api.newapiresponse.ResultWrapper
import com.example.nytimes.data.api.newapiresponse.safeApiCall
import com.example.nytimes.data.db.ArticleDao
import com.example.nytimes.data.model.ArticleResponse
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class HomeFragmentRepo @Inject constructor(
    private val apiService: ApiService,
    private val articleDao: ArticleDao
) {

    private val dispatcher: CoroutineDispatcher = Dispatchers.IO


    suspend fun getArticlesResponse(section: String, period: Int, apiKey: String) =
        apiService.getArticlesResponseAsync(section, period, apiKey)


    suspend fun getArticlesResponseTwo(
        section: String,
        period: Int,
        apiKey: String
    ): ResultWrapper<ArticleResponse> {

        return safeApiCall(articleDao, dispatcher) {
            apiService.getArticlesResponseAsyncTwo(section, period, apiKey)
        }
    }


    suspend fun getArticlesResponseFlow(
        section: String,
        period: Int,
        apiKey: String
    ): Flow<ResultWrapper<ArticleResponse>> {


        return flow {

            val dataDbSource = articleDao.getAllArticles()
            val articleResponse = ArticleResponse("", 20, dataDbSource, "OK")

            emit(ResultWrapper.Success(articleResponse))

            //  emit(ResultWrapper.Success(ArticleResponse("", 20, dataDbSource, "Ok")))


            val apiResponse = safeApiCall(articleDao, dispatcher) {
                apiService.getArticlesResponseAsyncTwo(section, period, apiKey)
            }


            when (apiResponse) {
                is ResultWrapper.Success -> {
                    articleDao.insertArticles(apiResponse.value.results)
                }
                else -> {
                    emit(apiResponse)

                    val dataDbSource = articleDao.getAllArticles()
                    val articleResponse = ArticleResponse("", 20, dataDbSource, "OK")

                    emit(ResultWrapper.Success(articleResponse))
                }
            }


//            emit(safeApiCall(articleDao, dispatcher) {
//                apiService.getArticlesResponseAsyncTwo(section, period, apiKey)
//            })

//            safeApiCall(articleDao, dispatcher) {
//                apiService.getArticlesResponseAsyncTwo(section, period, apiKey)
//            }

        }
    }


}