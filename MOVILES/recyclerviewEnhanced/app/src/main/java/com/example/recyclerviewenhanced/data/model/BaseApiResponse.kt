package com.example.recyclerviewenhanced.data.model

import com.example.recyclerviewenhanced.utils.NetworkResultt
import retrofit2.Response

abstract class BaseApiResponse {

    suspend fun <T,R> safeApiCall(apiCall: suspend () -> Response<R>,transform :(R) -> T ): NetworkResultt<T> {
        try {
            val response = apiCall()
            if (response.isSuccessful) {
                val body = response.body()
                body?.let {
                    return NetworkResultt.Success(transform(body))
                }
            }
            return error("${response.code()} ${response.message()}")
        } catch (e: Exception) {
            return error(e.message ?: e.toString())
        }
    }

    suspend fun <T> safeApiCall(apiCall: suspend () -> Response<T>): NetworkResultt<T> {
        try {
            val response = apiCall()
            if (response.isSuccessful) {
                val body = response.body()
                body?.let {
                    return NetworkResultt.Success(body)
                }
            }
            return error("${response.code()} ${response.message()}")
        } catch (e: Exception) {
            return error(e.message ?: e.toString())
        }
    }

    private fun <T> error(errorMessage: String): NetworkResultt<T> =
        NetworkResultt.Error("Api call failed $errorMessage")

}