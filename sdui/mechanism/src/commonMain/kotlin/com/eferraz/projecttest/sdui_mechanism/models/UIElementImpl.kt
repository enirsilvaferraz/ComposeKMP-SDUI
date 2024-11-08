package com.eferraz.projecttest.sdui_mechanism.models

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.eferraz.projecttest.sdui_mechanism.SDUIScreenScope

abstract class UIComponentImpl<Element : UIComponent> {

    @Composable
    abstract fun SDUIScreenScope.build(modifier: Modifier = Modifier, component: Element)
}

abstract class UIActionImpl<Action : UIAction> {

    abstract fun SDUIScreenScope.build(action: Action)
}