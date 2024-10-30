package com.eferraz.projecttest.sdui_mechanism

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier

internal abstract class UIElementComposable<Element : UIElement> {

    @Composable
    abstract fun SDUIContainerScope.build(modifier: Modifier = Modifier, component: Element)
}