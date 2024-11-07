package com.eferraz.projecttest.sdui_solution

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.eferraz.projecttest.sdui_mechanism.models.UIComponent
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

internal class SDUIViewModel(
    private val useCase: GetScreenUseCase
) : ViewModel() {

    private val _state: MutableStateFlow<ScreenState> = MutableStateFlow(ScreenState.Loading)
    val state = _state.asStateFlow()

    init {
        viewModelScope.launch {
            _state.update { ScreenState.Success(useCase()) }
        }
    }

    sealed class ScreenState {

        data object Loading : ScreenState()
        class Success(val screen: UIComponent) : ScreenState()
    }
}