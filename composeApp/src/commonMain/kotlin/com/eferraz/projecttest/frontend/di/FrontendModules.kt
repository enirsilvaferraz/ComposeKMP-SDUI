package com.eferraz.projecttest.frontend.di

import com.eferraz.projecttest.frontend.SDUIComponentComposable
import com.eferraz.projecttest.frontend.SDUIScreenComposable
import com.eferraz.projecttest.frontend.SDUITextComposable
import com.eferraz.projecttest.frontend.SDUIViewModel
import org.koin.core.module.dsl.factoryOf
import org.koin.core.module.dsl.viewModelOf
import org.koin.core.qualifier.named
import org.koin.dsl.bind
import org.koin.dsl.module


val frontendModule = module {

    viewModelOf(::SDUIViewModel)

    factory(named("sdui-screen")) { SDUIScreenComposable() } bind SDUIComponentComposable::class
    factory(named("sdui-text")) { SDUITextComposable() } bind SDUIComponentComposable::class

}