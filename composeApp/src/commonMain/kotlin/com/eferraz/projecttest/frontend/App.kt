package com.eferraz.projecttest.frontend

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.toRoute
import com.eferraz.projecttest.backend.di.backendModule
import com.eferraz.projecttest.frontend.core.DefaultScreen
import com.eferraz.projecttest.frontend.di.frontendModule
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
            modules(backendModule, frontendModule)
        }
    ) {

        val navController: NavHostController = rememberNavController()

        NavHost(
            navController = navController,
            startDestination = NavRoot.Home("/home")
        ) {

            composable<NavRoot.Home> {
                DefaultScreen(
                    vm = koinViewModel(parameters = { parametersOf(it.toRoute<NavRoot.Home>().url) })
                )
            }
        }
    }
}

private sealed class NavRoot {

    @Serializable
    class Home(val url: String) : NavRoot()
}