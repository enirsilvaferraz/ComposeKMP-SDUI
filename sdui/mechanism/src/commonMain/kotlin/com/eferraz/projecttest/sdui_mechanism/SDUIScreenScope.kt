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
import com.eferraz.projecttest.sdui_mechanism.models.UIModifier
import com.eferraz.projecttest.sdui_mechanism.models.UIModifierImpl
import com.eferraz.projecttest.sdui_mechanism.models.UIScope
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

    companion object {

        /**
         * Builder of [SDUIScreenScope].
         */
        @OptIn(ExperimentalFoundationApi::class)
        @Composable
        fun build(
            navController: NavHostController = rememberNavController(),
            pagerState: MutableState<PagerState?> = mutableStateOf(null),
            coroutineScope: CoroutineScope = rememberCoroutineScope(),
        ) = SDUIScreenScope(
            navController = navController,
            pagerState = pagerState,
            coroutineScope = coroutineScope
        )
    }
}

object SDUIScope : KoinComponent {

    /**
     * Method to handle an [UIComponent] element implementation.
     */
    @Composable
    inline fun <reified Component : UIComponent, Scope : UIScope> Component.build(scope: Scope) =
        with(get<UIComponentImpl<Component, Scope>>(named(this@build.serial()))) {
            scope.build(component = this@build, modifier = this@build.modifier.build())
        }

    /**
     * Method to handle an [UIAction] element implementation.
     */
    inline fun <reified Action : UIAction, Scope : UIScope> Action.build(scope: Scope) =
        with(get<UIActionImpl<Action, Scope>>(named(this@build.serial()))) {
            scope.build(action = this@build)
        }

    /**
     * Method to handle an [UIModifier] element implementation.
     */
    inline fun <reified Modifier : UIModifier> List<Modifier>.build(): androidx.compose.ui.Modifier {

        var modifier: androidx.compose.ui.Modifier = Modifier

        this.forEach { itModifier ->
            with(get<UIModifierImpl<Modifier>>(named(itModifier.serial()))) {
                modifier = modifier.build(modifier = itModifier)
            }
        }

        return modifier
    }

    /**
     * @return the serialization name of an [UIElement] described by [SerialName] annotation.
     */
    @OptIn(ExperimentalSerializationApi::class, InternalSerializationApi::class)
    inline fun <reified Element : UIElement> Element.serial(): String = this::class.serializer().descriptor.serialName
}
