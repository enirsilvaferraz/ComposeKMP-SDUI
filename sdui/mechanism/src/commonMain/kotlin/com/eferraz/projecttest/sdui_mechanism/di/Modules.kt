package com.eferraz.projecttest.sdui_mechanism.di

import com.eferraz.projecttest.sdui_mechanism.configurePolymorphism
import kotlinx.serialization.json.Json
import kotlinx.serialization.modules.SerializersModule
import org.koin.dsl.module

val mechanismModule = module {
    factory {
        Json {
            prettyPrint = true
            serializersModule = SerializersModule {
                configurePolymorphism(this)
            }
        }
    }
}