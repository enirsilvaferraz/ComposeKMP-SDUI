package com.eferraz.projecttest.sdui_mechanism.models

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier

abstract class UIElementImpl<Element : UIElement>

abstract class UIComponentImpl<Element : UIComponent, Scope : UIScope> : UIElementImpl<Element>() {

    @Composable
    abstract fun Scope.build(modifier: Modifier = Modifier, component: Element)
}

abstract class UIActionImpl<Action : UIAction, Scope : UIScope> : UIElementImpl<Action>() {

    abstract fun Scope.build(action: Action)
}

abstract class UIModifierImpl<Modifier : UIModifier> : UIElementImpl<Modifier>() {

    abstract fun androidx.compose.ui.Modifier.build(modifier: Modifier): androidx.compose.ui.Modifier
}