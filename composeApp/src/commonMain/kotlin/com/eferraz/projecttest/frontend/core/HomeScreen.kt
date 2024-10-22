package com.eferraz.projecttest.frontend.core

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import com.eferraz.projecttest.frontend.core.SDUIViewModel.ScreenState
import org.koin.compose.viewmodel.koinViewModel

@Composable
internal fun DefaultScreen(
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
    state.screen.build()
}