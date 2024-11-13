package com.eferraz.projecttest.sdui_components

import com.eferraz.projecttest.sdui_mechanism.registerAction
import com.eferraz.projecttest.sdui_mechanism.registerComponent
import com.eferraz.projecttest.sdui_mechanism.registerModifier
import org.koin.dsl.module

val componentModule = module {

    registerComponent(UIScaffoldComponentImpl())
    registerComponent(UITextComponentImpl())
    registerComponent(UITopBarComponentImpl())
    registerComponent(UILazyColumnComponentImpl())
    registerComponent(UIIconComponentImpl())
    registerComponent(UIBottomBarComponentImpl())
    registerComponent(UIHorizontalPagerComponentImpl())
    registerComponent(UIRowComponentImpl())
    registerComponent(UIImageComponentImpl())

    registerAction(UINavigateImpl())
    registerAction(UIChangePageImpl())

    registerModifier(UIPaddingImpl())
    registerModifier(UISizeImpl())
    registerModifier(UIBackgroundImpl())
    registerModifier(UIFillMaxWidthImpl())
}