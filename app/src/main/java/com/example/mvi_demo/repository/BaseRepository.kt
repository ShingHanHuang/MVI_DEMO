package com.example.mvi_demo.repository

import android.util.Log
import com.example.mvi_demo.api.ApiResult
import com.example.mvi_demo.api.HttpResponse

open class BaseRepository {

    protected suspend fun <T> Call(apiCall: suspend () -> HttpResponse<T>): ApiResult<T> {
        return try {
            val response = apiCall()
            Log.d("API", "Response: $response")
            if ((response.code == 200 ||response.code == 0)&& response.data != null) {
                ApiResult.Success(response.data)
            } else {
                Log.e("API", "Error message: ${response.message}")
                ApiResult.Failure(Exception(response.message))
            }
        } catch (e: Exception) {
            ApiResult.Failure(e)
        }
    }
}
