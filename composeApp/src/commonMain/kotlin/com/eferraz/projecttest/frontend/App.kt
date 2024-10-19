package com.eferraz.projecttest.frontend

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.eferraz.projecttest.backend.di.backendModule
import com.eferraz.projecttest.frontend.di.frontendModule
import org.jetbrains.compose.ui.tooling.preview.Preview
import org.koin.compose.KoinApplication

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
            startDestination = "home"
        ) {

            composable(route = "home") {
                SDUIContainer()
            }
        }
    }
}

