package com.example.mvi_demo.intent


sealed class LoginIntent : IUiIntent {
    data class Login(val username: String, val password: String) : LoginIntent()
}

data class LoginState(
    val isLoading: Boolean = false) : IUiState

sealed class LoginEffect : IUIEffect {
    data class ErrorMessage(val error: String? = null) : LoginEffect()
    class ShowToast(val text: String) : LoginEffect()
    object NavigationToSchoolDetail : LoginEffect()
}