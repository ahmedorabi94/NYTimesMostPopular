package com.example.nytimes.data.api

import okhttp3.Request
import okhttp3.ResponseBody
import okio.Timeout
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Converter
import retrofit2.Response
import timber.log.Timber
import java.io.IOException

internal class NetworkResponseCall<S : Any, E : Any>(
    private val delegate: Call<S>,
    private val errorConverter: Converter<ResponseBody, E>
) : Call<NetworkResponse<S, E>> {

    override fun enqueue(callback: Callback<NetworkResponse<S, E>>) {
        return delegate.enqueue(object : Callback<S> {
            override fun onResponse(call: Call<S>, response: Response<S>) {
                val body = response.body()
                val code = response.code()
                val error = response.errorBody()

                if (response.isSuccessful) {
                    Timber.e("onResponse $body")

                    if (body != null) {
                        callback.onResponse(
                            this@NetworkResponseCall,
                            Response.success(NetworkResponse.Success(body))
                        )
                    } else {
                        // Response is successful but the body is null
                        callback.onResponse(
                            this@NetworkResponseCall,
                            Response.success(NetworkResponse.UnknownError(null))
                        )
                    }
                } else {

                    val errorBody = when {
                        error == null -> {
                            null
                        }
                        error.contentLength() == 0L -> null
                        else -> try {
                            errorConverter.convert(error)
                        } catch (ex: Exception) {
                            null
                        }
                    }


                    Timber.e("Else $errorBody $error  $code")

                    when (code) {
                        404 -> {

                            callback.onResponse(
                                this@NetworkResponseCall,
                                Response.success(
                                    NetworkResponse.ApiError(
                                        errorBody,
                                        "404 Not Found"
                                    )
                                )
                            )

                        }
                        401 -> {
                            callback.onResponse(
                                this@NetworkResponseCall,
                                Response.success(
                                    NetworkResponse.ApiError(
                                        errorBody,
                                        "401 Unauthorized"
                                    )
                                )
                            )

                        }
                        409 -> {
                            callback.onResponse(
                                this@NetworkResponseCall,
                                Response.success(
                                    NetworkResponse.ApiError(
                                        errorBody,
                                        "409 Conflict"
                                    )
                                )
                            )

                        }
                        500 -> {
                            callback.onResponse(
                                this@NetworkResponseCall,
                                Response.success(
                                    NetworkResponse.ApiError(
                                        errorBody,
                                        "500 internal server error"
                                    )
                                )
                            )

                        }
                    }

                }
            }

            override fun onFailure(call: Call<S>, throwable: Throwable) {
                Timber.e("onFailure $throwable")


                val networkResponse = when (throwable) {
                    is IOException -> NetworkResponse.NetworkError(throwable)
                    else -> NetworkResponse.UnknownError(throwable)
                }
                callback.onResponse(this@NetworkResponseCall, Response.success(networkResponse))
            }
        })
    }

    override fun isExecuted() = delegate.isExecuted

    override fun clone() = NetworkResponseCall(delegate.clone(), errorConverter)

    override fun isCanceled() = delegate.isCanceled

    override fun cancel() = delegate.cancel()

    override fun execute(): Response<NetworkResponse<S, E>> {
        throw UnsupportedOperationException("NetworkResponseCall doesn't support execute")
    }

    override fun timeout(): Timeout = delegate.timeout()

    override fun request(): Request = delegate.request()
}