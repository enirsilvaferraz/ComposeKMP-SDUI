package com.eferraz.projecttest.sdui_solution

import org.koin.core.module.dsl.factoryOf
import org.koin.core.module.dsl.viewModelOf
import org.koin.dsl.module

val solutionModule = module {

    viewModelOf(::SDUIViewModel)
    factoryOf(::GetScreenUseCase)
    factoryOf(::ScreenRepository)
}