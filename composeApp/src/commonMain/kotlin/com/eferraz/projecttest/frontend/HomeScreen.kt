package com.eferraz.projecttest.frontend

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.eferraz.projecttest.backend.api.HomeApi
import com.eferraz.projecttest.backend.api.SDUIComponent
import com.eferraz.projecttest.backend.api.SDUIScreen
import com.eferraz.projecttest.backend.api.SDUIText
import com.eferraz.projecttest.frontend.SDUIViewModel.ScreenState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import kotlinx.serialization.SerialName
import kotlinx.serialization.json.Json
import org.koin.compose.koinInject
import org.koin.compose.viewmodel.koinViewModel
import org.koin.core.qualifier.named

@Composable
internal fun SDUIContainer(
    vm: SDUIViewModel = koinViewModel()
) {

    MaterialTheme {

        val state by vm.state.collectAsState()

        when (state) {
            ScreenState.Loading -> LoadingScreen()
            is ScreenState.Success -> SuccessScreen(state as ScreenState.Success)
        }
    }
}

@Composable
private fun LoadingScreen() {

    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        Text("Loading")
    }
}

@Composable
private fun SuccessScreen(
    state: ScreenState.Success
) {

    Column {
        koinInject<SDUIComponentComposable<SDUIComponent>>(named(state.screen.getSerialName())).build(state.screen)
    }
}

internal class SDUIViewModel(
    private val homeApi: HomeApi
) : ViewModel() {

    private val _state: MutableStateFlow<ScreenState> = MutableStateFlow(ScreenState.Loading)
    val state = _state.asStateFlow()

    init {
        viewModelScope.launch {
            val result = homeApi.get()
            _state.update {ScreenState.Success(Json.decodeFromString(result)) }
        }
    }

    sealed class ScreenState {

        data object Loading : ScreenState()
        class Success(val screen: SDUIComponent) : ScreenState()
    }
}


internal abstract class SDUIComponentComposable<T : SDUIComponent> {

    @Composable
    abstract fun build(component: T)
}

internal class SDUIScreenComposable : SDUIComponentComposable<SDUIScreen>() {

    @Composable
    override fun build(component: SDUIScreen) {

        Text(component.getSerialName())

        LazyColumn {

            items(component.body) {
                koinInject<SDUIComponentComposable<SDUIComponent>>(named(it.getSerialName())).build(it)
            }
        }
    }
}

internal class SDUITextComposable : SDUIComponentComposable<SDUIText>() {

    @Composable
    override fun build(component: SDUIText) {
        Text(component.text)
    }
}