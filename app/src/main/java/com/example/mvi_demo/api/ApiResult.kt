package com.example.mvi_demo.api

sealed class ApiResult<out T> {
    data class Success<out T>(val data: T) : ApiResult<T>()
    data class Failure(val error: Throwable) : ApiResult<Nothing>()
}
