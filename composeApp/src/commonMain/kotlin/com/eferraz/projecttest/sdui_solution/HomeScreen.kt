package com.eferraz.projecttest.sdui_solution

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import com.eferraz.projecttest.sdui_mechanism.SDUIScope.build
import com.eferraz.projecttest.sdui_mechanism.SDUIScreenScope
import com.eferraz.projecttest.sdui_mechanism.models.UIAnyScope
import com.eferraz.projecttest.sdui_solution.SDUIViewModel.ScreenState
import org.koin.compose.viewmodel.koinViewModel

@OptIn(ExperimentalFoundationApi::class)
@Composable
internal fun DefaultScreen(
    vm: SDUIViewModel = koinViewModel(),
) {

    val state by vm.state.collectAsState()

    with(UIAnyScope(SDUIScreenScope.build())) {
        when (state) {
            ScreenState.Loading -> LoadingScreen()
            is ScreenState.Success -> SuccessScreen(state as ScreenState.Success)
        }
    }
}

@Composable
private fun UIAnyScope.LoadingScreen() {

    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        Text("Loading")
    }
}

@Composable
private fun UIAnyScope.SuccessScreen(
    state: ScreenState.Success,
) {
    state.screen.build(this)
}