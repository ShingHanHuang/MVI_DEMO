package com.example.mvi_demo.viewmodel


import android.util.Log
import androidx.lifecycle.viewModelScope
import com.example.mvi_demo.api.ApiResult
import com.example.mvi_demo.intent.CarParkInfoEffect
import com.example.mvi_demo.intent.CarParkInfoIntent
import com.example.mvi_demo.intent.CarParkInfoState
import com.example.mvi_demo.intent.IUiIntent
import com.example.mvi_demo.repository.CarParkInfoRepository
import kotlinx.coroutines.async
import kotlinx.coroutines.launch


class CarParkInfoViewModel(private val repository: CarParkInfoRepository) :
    BaseViewModel<CarParkInfoState, CarParkInfoIntent, CarParkInfoEffect>() {

    init {
        getCarParkData()
    }

    override fun initUiState(): CarParkInfoState {
        return CarParkInfoState(false, null);
    }

    override fun handleIntent(intent: IUiIntent) {
        when (intent) {
            is CarParkInfoIntent.GetCarParkData -> getCarParkData()
        }
    }

    private fun getCarParkData() {
        sendUiState { copy(isLoading = true) }
        viewModelScope.launch {
            val detailDeferred = async { repository.getAllCarParkDetail() }
            val availableDeferred = async { repository.getAvailableCarParkDetail() }

            val detailResult = detailDeferred.await()
            val availableResult = availableDeferred.await()

            Log.e("peter","detailResult"+detailResult)
            Log.e("peter","availableResult"+availableResult)
            if (detailResult is ApiResult.Success && availableResult is ApiResult.Success) {
                sendUiState { copy(isLoading = false) }
                val mappedList =
                    repository.mappingCarParkData(detailResult.data, availableResult.data)
                sendUiState { copy(isLoading = false, mappedList) }
            } else {
                sendUiState { copy(isLoading = false) }
                val error = when {
                    detailResult is ApiResult.Failure -> detailResult.error.message
                        ?: "Error retrieving detail"

                    availableResult is ApiResult.Failure -> availableResult.error.message
                        ?: "Error retrieving available info"

                    else -> "Unknown error"
                }
                sendEffect { CarParkInfoEffect.ErrorMessage(error) }
            }
        }
    }


}