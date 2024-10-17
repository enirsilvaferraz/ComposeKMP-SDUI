package com.eferraz.projecttest

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Button
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.eferraz.projecttest.network.*
import org.jetbrains.compose.resources.painterResource
import org.jetbrains.compose.ui.tooling.preview.Preview

import composekotlinproject.composeapp.generated.resources.Res
import composekotlinproject.composeapp.generated.resources.compose_multiplatform
import io.ktor.client.*
import kotlinx.coroutines.launch
import org.koin.compose.KoinApplication
import org.koin.compose.koinInject
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.module

@Composable
@Preview
fun App(
    navController: NavHostController = rememberNavController()
) {
    KoinApplication(
        application = {
            modules(appModule)
        }
    ) {

        NavHost(
            navController = navController,
            startDestination = "home"
        ) {

            composable(route = "home") {
                HomeScreen(
                    onNavigate = { navController.navigate("secound") }
                )
            }

            composable(route = "secound") {
                SecoundScreen(
                    onNavigate = { navController.navigateUp() }
                )
            }
        }
    }
}

@Composable
fun HomeScreen(
    onNavigate: () -> Unit
) {

    MaterialTheme {

//        var showContent by remember { mutableStateOf(false) }

        Column(Modifier.fillMaxWidth(), horizontalAlignment = Alignment.CenterHorizontally) {

            Button(
//                onClick = { showContent = !showContent }
                onClick = onNavigate
            ) {
                Text("Click me!")
            }

//            AnimatedVisibility(showContent) {
//            
//                val greeting = remember { Greeting().greet() }
//                
//                Column(Modifier.fillMaxWidth(), horizontalAlignment = Alignment.CenterHorizontally) {
//                    Image(painterResource(Res.drawable.compose_multiplatform), null)
//                    Text("Compose: $greeting")
//                }
//            }
        }
    }
}

@Composable
fun SecoundScreen(
    ds: DataSourceRemote = koinInject(),
    onNavigate: () -> Unit
) {

    MaterialTheme {

        Column {

            Text("Secound Screen")

            Button(
                onClick = onNavigate
            ) {
                Text("Click me!")
            }

            val (models, setModels) = remember { mutableStateOf<PaginationResult<PokemonRef>?>(null) }

            val scope = rememberCoroutineScope()
            scope.launch {
                setModels(ds.get())
            }

            models?.results?.let {

                LazyColumn {

                    items(it) {
                        Text(text = it.name)
                    }
                }

            }
        }
    }
}

val appModule = module {

    single { createHttpClient() }

    singleOf(::DataSourceRemote)
}