package com.example.mvi_demo.intent

import com.example.mvi_demo.data.CarParkInfo

sealed class CarParkInfoIntent : IUiIntent {
    object GetCarParkData : CarParkInfoIntent()
}

data class CarParkInfoState(
    val isLoading: Boolean = false,
    val carParkList: ArrayList<CarParkInfo>?
) : IUiState

sealed class CarParkInfoEffect : IUIEffect {
    class ShowToast(val text: String) : CarParkInfoEffect()
    data class ErrorMessage(val error: String) : CarParkInfoEffect()
}