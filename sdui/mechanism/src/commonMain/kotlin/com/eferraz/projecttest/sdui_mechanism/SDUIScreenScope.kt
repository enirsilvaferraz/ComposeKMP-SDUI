package com.eferraz.projecttest.sdui_mechanism

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.pager.PagerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.eferraz.projecttest.sdui_mechanism.models.UIAction
import com.eferraz.projecttest.sdui_mechanism.models.UIActionBehavior
import com.eferraz.projecttest.sdui_mechanism.models.UIComponent
import com.eferraz.projecttest.sdui_mechanism.models.UIElement
import com.eferraz.projecttest.sdui_mechanism.models.UIElementComposable
import kotlinx.coroutines.CoroutineScope
import kotlinx.serialization.ExperimentalSerializationApi
import kotlinx.serialization.InternalSerializationApi
import kotlinx.serialization.SerialName
import kotlinx.serialization.serializer
import org.koin.core.component.KoinComponent
import org.koin.core.component.get
import org.koin.core.qualifier.named

/**
 * Escopo de tela, usado para dar contexto a todos os elementos do SDUI.
 * Compartilha configurações da mesma forma que uma tela composable.
 */
class SDUIScreenScope @OptIn(ExperimentalFoundationApi::class) private constructor(
    val navController: NavHostController,
    val pagerState: MutableState<PagerState?>,
    val coroutineScope: CoroutineScope
) : KoinComponent {


    /**
     * Método para renderizar um elemento do tipo [UIComponent].
     */
    @Composable
    inline fun <reified Element : UIComponent> Element.build() {
        with(get<UIElementComposable<Element>>(named(this.serial()))) {
            build(component = this@build, modifier = Modifier)
        }
    }

    /**
     * Método para renderizar uma lista de elementos do tipo [UIComponent].
     */
    @Composable
    inline fun <reified Element : UIComponent> List<Element>.build() {
        this.map { it.build() }
    }

    /**
     * Método para executar um elemento do tipo [UIAction].
     */
    inline fun <reified Action : UIAction> Action.build() {
        with(get<UIActionBehavior<Action>>(named(this@build.serial()))) {
            build(action = this@build)
        }
    }

    /**
     * Retorna o nome de serialização do [UIElement] descriminado na anotação [SerialName].
     */
    @OptIn(ExperimentalSerializationApi::class, InternalSerializationApi::class)
    inline fun <reified Element : UIElement> Element.serial(): String = this::class.serializer().descriptor.serialName

    companion object {

        /**
         * Método para criar um [SDUIScreenScope].
         */
        @OptIn(ExperimentalFoundationApi::class)
        @Composable
        fun build(
            navController: NavHostController = rememberNavController(),
            pagerState: MutableState<PagerState?> = mutableStateOf(null),
            coroutineScope: CoroutineScope = rememberCoroutineScope()
        ) = SDUIScreenScope(
            navController = navController,
            pagerState = pagerState,
            coroutineScope = coroutineScope
        )
    }
}