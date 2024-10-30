package com.eferraz.projecttest.sdui_mechanism

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.pager.PagerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.rememberCoroutineScope
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import kotlinx.coroutines.CoroutineScope
import org.koin.compose.koinInject
import org.koin.core.component.KoinComponent
import org.koin.core.component.get
import org.koin.core.qualifier.named

internal class SDUIContainerScope @OptIn(ExperimentalFoundationApi::class) private constructor(
    val navController: NavHostController,
    val pagerState: MutableState<PagerState?>,
    val coroutineScope: CoroutineScope
) : KoinComponent {


    @Composable
    internal inline fun <reified Element : UIElement> Element.build() {
        with(koinInject<UIElementComposable<Element>>(named(this.serial()))) {
            build(component = this@build)
        }
    }

    @Composable
    internal inline fun <reified Element : UIElement> List<Element>.build() {
        this.map { it.build() }
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