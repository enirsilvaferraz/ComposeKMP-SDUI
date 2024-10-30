package com.eferraz.projecttest.frontend.di

import com.eferraz.projecttest.sdui_mechanism.sduiPolymorphicComponents
import kotlinx.serialization.json.Json
import org.koin.dsl.module


val frontendModule = module {

    factory { Json { serializersModule = sduiPolymorphicComponents() } }
}