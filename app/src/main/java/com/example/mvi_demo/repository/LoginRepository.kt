package com.example.mvi_demo.repository

import com.example.mvi_demo.api.ApiResult
import com.example.mvi_demo.api.LoginService
import com.example.mvi_demo.data.JsonBody
import com.example.mvi_demo.data.UserInfo

class LoginRepository(private val apiService: LoginService) : BaseRepository() {

    suspend fun login(jsonBody: JsonBody): ApiResult<UserInfo> {
        return Call { apiService.login(jsonBody) }
    }
}