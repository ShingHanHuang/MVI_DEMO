package com.example.mvi_demo.viewmodel

import android.util.Log
import androidx.lifecycle.viewModelScope
import com.example.mvi_demo.api.ApiResult
import com.example.mvi_demo.data.JsonBody
import com.example.mvi_demo.intent.LoginIntent
import com.example.mvi_demo.intent.LoginState
import com.example.mvi_demo.intent.IUiIntent
import com.example.mvi_demo.intent.LoginEffect
import com.example.mvi_demo.repository.LoginRepository
import kotlinx.coroutines.launch
import org.json.JSONObject


class LoginViewModel(private val repository: LoginRepository) :
    BaseViewModel<LoginState, LoginIntent, LoginEffect>() {


    override fun initUiState(): LoginState {
        return LoginState(false)
    }

    override fun handleIntent(intent: IUiIntent) {
        when (intent) {
            is LoginIntent.Login -> login(intent.username, intent.password)
        }
    }

    private fun login(username: String, password: String) {
        Log.e("peter","login"+username)
        sendUiState { copy(isLoading = true) }
        val loginInfo = JsonBody.create(JSONObject().apply {
            put("username", username)
            put("password", password)
        }.toString())

        viewModelScope.launch {
            when (val result = repository.login(loginInfo)) {
                is ApiResult.Success -> {
                    Log.e("peter","ApiResult.Success")
                    sendUiState { copy(isLoading = false) }
                    sendEffect { LoginEffect.NavigationToSchoolDetail }
                }

                is ApiResult.Failure -> {
                    Log.e("peter","ApiResult.Failure")
                    sendUiState { copy(isLoading = false) }
                    sendEffect { LoginEffect.ErrorMessage(result.error.message) }
                }
            }
        }
    }

}