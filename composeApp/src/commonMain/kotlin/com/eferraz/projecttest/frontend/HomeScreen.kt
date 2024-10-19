package com.eferraz.projecttest.frontend

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import com.eferraz.projecttest.backend.api.SDUIComponent
import com.eferraz.projecttest.frontend.SDUIViewModel.ScreenState
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