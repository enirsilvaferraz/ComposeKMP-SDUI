package com.eferraz.projecttest.sdui_mechanism

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.pager.PagerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.eferraz.projecttest.sdui_mechanism.models.UIAction
import com.eferraz.projecttest.sdui_mechanism.models.UIActionImpl
import com.eferraz.projecttest.sdui_mechanism.models.UIBackground
import com.eferraz.projecttest.sdui_mechanism.models.UIComponent
import com.eferraz.projecttest.sdui_mechanism.models.UIComponentImpl
import com.eferraz.projecttest.sdui_mechanism.models.UIElement
import com.eferraz.projecttest.sdui_mechanism.models.UIElementImpl
import com.eferraz.projecttest.sdui_mechanism.models.UIFillMaxWidth
import com.eferraz.projecttest.sdui_mechanism.models.UIModifier
import com.eferraz.projecttest.sdui_mechanism.models.UIPadding
import com.eferraz.projecttest.sdui_mechanism.models.UISize
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
                build(component = this@build, modifier = this@build.modifier.build())
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

fun List<UIModifier>.build(): Modifier {

    var modifier: Modifier = Modifier

    this@build.forEach {
        modifier = when (it) {
            is UIPadding -> modifier.padding(start = it.start.dp, top = it.top.dp, end = it.end.dp, bottom = it.bottom.dp)
            is UISize -> modifier.size(width = it.width.dp, height = it.height.dp)
            is UIBackground -> modifier.background(color = Color(it.color))
            is UIFillMaxWidth -> modifier.fillMaxWidth()
        }
    }

    return modifier
}

/**
 * @return the serialization name of an [UIElement] described by [SerialName] annotation.
 */
@OptIn(ExperimentalSerializationApi::class, InternalSerializationApi::class)
inline fun <reified Element : UIElement> Element.serial(): String = this::class.serializer().descriptor.serialName
