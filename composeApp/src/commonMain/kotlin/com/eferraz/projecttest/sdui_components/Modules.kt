package com.eferraz.projecttest.sdui_components

import com.eferraz.projecttest.sdui_mechanism.registerComponent
import org.koin.dsl.module

val componentModule = module {

    registerComponent(UIScaffold::class, UIScaffoldComposable())
    registerComponent(UIText::class, UITextComposable())
    registerComponent(UITopBar::class, UITopBarComposable())
    registerComponent(UILazyColumn::class, UILazyColumnComposable())
    registerComponent(UIIcon::class, UIIconComposable())
    registerComponent(UIBottomBar::class, UIBottomBarComposable())
    registerComponent(UIHorizontalPager::class, UIHorizontalPagerComposable())

    registerComponent(UINavigation::class, UINavigationBehavior())
    registerComponent(UIChangePage::class, UIChangePageBehavior())
}