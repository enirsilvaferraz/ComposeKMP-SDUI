package com.eferraz.projecttest.frontend

import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import com.eferraz.projecttest.backend.di.backendModule
import com.eferraz.projecttest.sdui_solution.DefaultScreen
import com.eferraz.projecttest.frontend.di.frontendModule
import com.eferraz.projecttest.sdui_components.componentModule
import com.eferraz.projecttest.sdui_solution.solutionModule
import kotlinx.serialization.Serializable
import org.jetbrains.compose.ui.tooling.preview.Preview
import org.koin.compose.KoinApplication
import org.koin.compose.viewmodel.koinViewModel
import org.koin.core.parameter.parametersOf

@Composable
@Preview
fun App() {

    KoinApplication(
        application = {
            modules(backendModule, frontendModule, componentModule, solutionModule)
        }
    ) {

        MaterialTheme {
            DefaultScreen(
                vm = koinViewModel(parameters = { parametersOf("/home") })
            )
        }
    }
}

@Serializable
sealed class NavRoot

