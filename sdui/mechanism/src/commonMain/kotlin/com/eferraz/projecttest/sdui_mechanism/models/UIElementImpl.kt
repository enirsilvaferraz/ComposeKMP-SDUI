package com.eferraz.projecttest.sdui_mechanism.models

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.eferraz.projecttest.sdui_mechanism.SDUIScreenScope

abstract class UIElementImpl<Element : UIElement>

abstract class UIComponentImpl<Element : UIComponent> : UIElementImpl<Element>() {

    @Composable
    abstract fun SDUIScreenScope.build(modifier: Modifier = Modifier, component: Element)
}

abstract class UIActionImpl<Action : UIAction> : UIElementImpl<Action>() {

    abstract fun SDUIScreenScope.build(action: Action)
}

abstract class UIModifierImpl<Modifier : UIModifier> : UIElementImpl<Modifier>() {

    abstract fun androidx.compose.ui.Modifier.build(modifier: Modifier): androidx.compose.ui.Modifier
}