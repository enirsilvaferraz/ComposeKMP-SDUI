package com.eferraz.projecttest.sdui_components

import com.eferraz.projecttest.sdui_mechanism.registerAction
import com.eferraz.projecttest.sdui_mechanism.registerComponent
import org.koin.dsl.module

val componentModule = module {

    registerComponent(UIScaffold::class, UIScaffoldComponentImpl())
    registerComponent(UIText::class, UITextComponentImpl())
    registerComponent(UITopBar::class, UITopBarComponentImpl())
    registerComponent(UILazyColumn::class, UILazyColumnComponentImpl())
    registerComponent(UIIcon::class, UIIconComponentImpl())
    registerComponent(UIBottomBar::class, UIBottomBarComponentImpl())
    registerComponent(UIHorizontalPager::class, UIHorizontalPagerComponentImpl())
    registerComponent(UIRow::class, UIRowComponentImpl())
    registerComponent(UIImage::class, UIImageComponentImpl())

//    registerComponent(UINavigation::class, UINavigationBehavior())
    registerAction(UIChangePage::class, UIChangePageImpl())
}