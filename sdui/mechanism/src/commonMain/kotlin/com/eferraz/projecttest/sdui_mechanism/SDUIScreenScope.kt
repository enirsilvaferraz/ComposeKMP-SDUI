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
import com.eferraz.projecttest.sdui_mechanism.models.UIActionImpl
import com.eferraz.projecttest.sdui_mechanism.models.UIComponent
import com.eferraz.projecttest.sdui_mechanism.models.UIComponentImpl
import com.eferraz.projecttest.sdui_mechanism.models.UIElement
import com.eferraz.projecttest.sdui_mechanism.models.UIElementImpl
import kotlinx.coroutines.CoroutineScope
import kotlinx.serialization.ExperimentalSerializationApi
import kotlinx.serialization.InternalSerializationApi
import kotlinx.serialization.SerialName
import kotlinx.serialization.serializer
import org.koin.core.component.KoinComponent
import org.koin.core.component.get
import org.koin.core.qualifier.named

/**
 * Screen scope for SDUI.
 * Shares the same configuration as a composable screen.
 */
class SDUIScreenScope @OptIn(ExperimentalFoundationApi::class) private constructor(
    val navController: NavHostController,
    val pagerState: MutableState<PagerState?>,
    val coroutineScope: CoroutineScope,
) : KoinComponent {

    /**
     * Method to handle an [UIComponent] element implementation.
     */
    @Composable
    inline fun <reified Component : UIComponent> Component.build() =
        with(get<UIElementImpl<Component>>(named(this.serial()))) {
            (this as? UIComponentImpl<Component>)?.let {
                build(component = this@build, modifier = Modifier)
            }
        }

    /**
     * Method to handle an [UIAction] element implementation.
     */
    inline fun <reified Action : UIAction> Action.build() =
        with(get<UIElementImpl<Action>>(named(this.serial()))) {
            (this as? UIActionImpl<Action>)?.let {
                build(action = this@build)
            }
        }

    /**
     * @return the serialization name of an [UIElement] described by [SerialName] annotation.
     */
    @OptIn(ExperimentalSerializationApi::class, InternalSerializationApi::class)
    inline fun <reified Element : UIElement> Element.serial(): String = this::class.serializer().descriptor.serialName

    companion object {

        /**
         * Builder of [SDUIScreenScope].
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