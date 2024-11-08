package com.eferraz.projecttest.sdui_components

import com.eferraz.projecttest.sdui_mechanism.registerElement
//import org.koin.core.annotation.ComponentScan
//import org.koin.core.annotation.Module
import org.koin.dsl.module

val componentModule = module {

    registerElement(UIScaffoldComponentImpl())
    registerElement(UITextComponentImpl())
    registerElement(UITopBarComponentImpl())
    registerElement(UILazyColumnComponentImpl())
    registerElement(UIIconComponentImpl())
    registerElement(UIBottomBarComponentImpl())
    registerElement(UIHorizontalPagerComponentImpl())
    registerElement(UIRowComponentImpl())
    registerElement(UIImageComponentImpl())

//    registerComponent(UINavigation::class, UINavigationBehavior())
    registerElement(UIChangePageImpl())
}

//@Module
//@ComponentScan("com.eferraz.projecttest.sdui_components")
//class KoinM