package com.eferraz.projecttest.frontend

import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import coil3.ImageLoader
import coil3.PlatformContext
import coil3.compose.setSingletonImageLoaderFactory
import coil3.memory.MemoryCache
import coil3.request.CachePolicy
import coil3.request.crossfade
import coil3.util.DebugLogger
import com.eferraz.projecttest.backend.di.backendModule
import com.eferraz.projecttest.platformModule
import com.eferraz.projecttest.sdui_components.componentModule
import com.eferraz.projecttest.sdui_solution.DefaultScreen
import com.eferraz.projecttest.sdui_solution.solutionModule
import kotlinx.serialization.Serializable
import org.jetbrains.compose.ui.tooling.preview.Preview
import org.koin.compose.KoinApplication
import org.koin.compose.KoinContext
import org.koin.compose.viewmodel.koinViewModel
import org.koin.core.context.startKoin
import org.koin.core.parameter.parametersOf
import org.koin.dsl.KoinAppDeclaration

@Composable
@Preview
fun App() {

    setSingletonImageLoaderFactory { context ->
        getAsyncImageLoader(context)
    }

    KoinContext {
        MaterialTheme {
            DefaultScreen(
                vm = koinViewModel(parameters = { parametersOf("/home") })
            )
        }
    }
}

@Serializable
sealed class NavRoot

fun getAsyncImageLoader(context: PlatformContext) =
    ImageLoader.Builder(context).memoryCachePolicy(CachePolicy.ENABLED).memoryCache {
        MemoryCache.Builder().maxSizePercent(context, 0.3).strongReferencesEnabled(true).build()
    }.crossfade(true).logger(DebugLogger()).build()

fun initKoin(config: KoinAppDeclaration? = null) {
    startKoin {
        config?.invoke(this)
        modules(backendModule, frontendModule, componentModule, solutionModule, platformModule)
    }
}