package com.eferraz.projecttest.frontend

import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import com.eferraz.projecttest.backend.di.backendModule
import com.eferraz.projecttest.sdui_components.componentModule
import com.eferraz.projecttest.sdui_mechanism.di.mechanismModule
import com.eferraz.projecttest.sdui_solution.DefaultScreen
import com.eferraz.projecttest.sdui_solution.solutionModule
import org.jetbrains.compose.ui.tooling.preview.Preview
import org.koin.compose.KoinContext
import org.koin.compose.viewmodel.koinViewModel
import org.koin.core.context.startKoin
import org.koin.core.parameter.parametersOf
import org.koin.dsl.KoinAppDeclaration

@Composable
@Preview
fun App() {
    KoinContext {
        MaterialTheme {
            DefaultScreen(
                vm = koinViewModel(parameters = { parametersOf("/home") })
            )
        }
    }
}

fun initKoin(config: KoinAppDeclaration? = null) {
    startKoin {
        config?.invoke(this)
        modules(mechanismModule, backendModule, componentModule, solutionModule)
    }
}