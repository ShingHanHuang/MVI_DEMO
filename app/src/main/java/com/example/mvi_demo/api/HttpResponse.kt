package com.example.mvi_demo.api

data class HttpResponse<T>(
    val code: Int,
    val message: String,
    val data: T?
)
