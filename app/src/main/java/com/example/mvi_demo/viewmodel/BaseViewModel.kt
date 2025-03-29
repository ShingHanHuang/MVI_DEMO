package com.example.mvi_demo.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.mvi_demo.intent.IUIEffect
import com.example.mvi_demo.intent.IUiIntent
import com.example.mvi_demo.intent.IUiState
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

abstract class BaseViewModel<UiState : IUiState, UiIntent : IUiIntent, UIEffect : IUIEffect> :
    ViewModel() {

    private val _uiStateFlow = MutableStateFlow(initUiState())
    val uiStateFlow: StateFlow<UiState> = _uiStateFlow
    private val _uiIntentFlow: Channel<UiIntent> = Channel()
    val uiIntentFlow: Flow<UiIntent> = _uiIntentFlow.receiveAsFlow()
    private val _effectFlow = MutableSharedFlow<UIEffect>()
    val uiEffectFlow: SharedFlow<UIEffect> = _effectFlow.asSharedFlow()

    protected abstract fun initUiState(): UiState

    init {
        viewModelScope.launch {
            uiIntentFlow.collect {
                handleIntent(it)
            }
        }
    }

    protected fun sendUiState(copy: UiState.() -> UiState) {
        _uiStateFlow.update { copy(_uiStateFlow.value) }
    }


    protected abstract fun handleIntent(intent: IUiIntent)

    protected fun sendEffect(builder: suspend () -> UIEffect?) = viewModelScope.launch {
        builder()?.let { _effectFlow.emit(it) }
    }

    fun sendUiIntent(uiIntent: UiIntent) {
        viewModelScope.launch {
            _uiIntentFlow.send(uiIntent)
        }
    }
}