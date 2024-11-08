package com.eferraz.projecttest.sdui_components

import com.eferraz.projecttest.sdui_mechanism.registerElement
import org.koin.dsl.module

val componentModule = module {

    registerElement(UIScaffold::class, UIScaffoldComponentImpl())
    registerElement(UIText::class, UITextComponentImpl())
    registerElement(UITopBar::class, UITopBarComponentImpl())
    registerElement(UILazyColumn::class, UILazyColumnComponentImpl())
    registerElement(UIIcon::class, UIIconComponentImpl())
    registerElement(UIBottomBar::class, UIBottomBarComponentImpl())
    registerElement(UIHorizontalPager::class, UIHorizontalPagerComponentImpl())
    registerElement(UIRow::class, UIRowComponentImpl())
    registerElement(UIImage::class, UIImageComponentImpl())

//    registerComponent(UINavigation::class, UINavigationBehavior())
    registerElement(UIChangePage::class, UIChangePageImpl())
}