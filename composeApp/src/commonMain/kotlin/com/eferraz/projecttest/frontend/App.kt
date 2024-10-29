package com.eferraz.projecttest.frontend

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.pager.PagerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.rememberCoroutineScope
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.toRoute
import com.eferraz.projecttest.backend.di.backendModule
import com.eferraz.projecttest.frontend.core.*
import com.eferraz.projecttest.frontend.di.frontendModule
import kotlinx.coroutines.CoroutineScope
import kotlinx.serialization.Serializable
import org.jetbrains.compose.ui.tooling.preview.Preview
import org.koin.compose.KoinApplication
import org.koin.compose.koinInject
import org.koin.compose.viewmodel.koinViewModel
import org.koin.core.component.KoinComponent
import org.koin.core.component.get
import org.koin.core.parameter.parametersOf
import org.koin.core.qualifier.named

@OptIn(ExperimentalFoundationApi::class)
@Composable
@Preview
fun App() {

    KoinApplication(
        application = {
            modules(backendModule, frontendModule)
        }
    ) {

        with(SDUIContainerScope.build()) {

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
}

@Serializable
 sealed class NavRoot {

    @Serializable
    class Home(val url: String) : NavRoot()
}

internal class SDUIContainerScope @OptIn(ExperimentalFoundationApi::class) private constructor(
    val navController: NavHostController,
    val pagerState: MutableState<PagerState?> ,
    val coroutineScope: CoroutineScope
) : KoinComponent {


    @Composable
    internal inline fun <reified Element : UIElement> Element.build() {
        with(koinInject<UIElementComposable<Element>>(named(this.serial()))) {
            build(component = this@build)
        }
    }


    internal inline fun <reified Action : UIAction> Action.build() {
        with(get<UIActionBehavior<Action>>(named(this@build.serial()))) {
            build(action = this@build)
        }
    }

    companion object {

        @OptIn(ExperimentalFoundationApi::class)
        @Composable
        fun build(
             navController: NavHostController = rememberNavController(),
             pagerState: MutableState<PagerState?> = mutableStateOf(null),
             coroutineScope: CoroutineScope = rememberCoroutineScope()
        ) = SDUIContainerScope(
            navController = navController,
            pagerState = pagerState,
            coroutineScope = coroutineScope
        )
    }
}