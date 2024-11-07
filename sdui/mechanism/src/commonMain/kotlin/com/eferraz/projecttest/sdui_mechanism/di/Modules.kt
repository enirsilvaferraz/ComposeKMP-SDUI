package com.eferraz.projecttest.sdui_mechanism.di

import com.eferraz.projecttest.sdui_mechanism.sduiPolymorphicComponents
import kotlinx.serialization.json.Json
import org.koin.dsl.module

val mechanismModule = module {

    factory { Json { serializersModule = sduiPolymorphicComponents() } }
}