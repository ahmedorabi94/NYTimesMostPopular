package com.example.nytimes.data.api.newapiresponse

import com.example.nytimes.data.api.ErrorResponse
import com.example.nytimes.data.db.ArticleDao
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.withContext
import retrofit2.HttpException
import timber.log.Timber
import java.io.IOException


suspend fun <T> safeApiCall(
    articleDao: ArticleDao,
    dispatcher: CoroutineDispatcher,
    apiCall: suspend () -> T
): ResultWrapper<T> {

    return withContext(dispatcher) {
        try {

//            val response = apiCall.invoke() as ArticleResponse
//
//            val dataDbSource = articleDao.getAllArticles()
//            Timber.e("$dataDbSource.size")
//
//            if (dataDbSource.isEmpty()) {
//                articleDao.insertArticles(response.results)
//            } else {
//                articleDao.deleteAllArticles()
//                articleDao.insertArticles(response.results)
//
//            }
//
//
//            val articleResponse = ArticleResponse("", 20, articleDao.getAllArticles(), "OK")


            ResultWrapper.Success(apiCall.invoke())


        } catch (throwable: Throwable) {
            Timber.e("${throwable.message}   ${throwable.localizedMessage}")
            when (throwable) {
                is IOException -> {
                    ResultWrapper.NetworkError
                }
                is HttpException -> {
                    val code = throwable.code()
                    val errorResponse = convertErrorBody(throwable)
                    ResultWrapper.Error(code, errorResponse)
                }
                else -> {
                    ResultWrapper.Error(null, null)
                }
            }
        }
    }

}

private fun convertErrorBody(throwable: HttpException): ErrorResponse? {


    val s = throwable.response()?.errorBody()?.string()
    Timber.e(s)

    return if (s != null) {
        ErrorResponse(s)
    } else {
        null
    }

}


