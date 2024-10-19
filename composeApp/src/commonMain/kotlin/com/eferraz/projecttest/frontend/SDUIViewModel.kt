package com.eferraz.projecttest.frontend

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.eferraz.projecttest.backend.api.HomeApi
import com.eferraz.projecttest.backend.api.SDUIComponent
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import kotlinx.serialization.json.Json

internal class SDUIViewModel(
    private val homeApi: HomeApi,
    private val json: Json
) : ViewModel() {

    private val _state: MutableStateFlow<ScreenState> = MutableStateFlow(ScreenState.Loading)
    val state = _state.asStateFlow()

    init {
        viewModelScope.launch {
            val result = homeApi.get()
            _state.update {ScreenState.Success(json.decodeFromString(result)) }
        }
    }

    sealed class ScreenState {

        data object Loading : ScreenState()
        class Success(val screen: SDUIComponent) : ScreenState()
    }
}