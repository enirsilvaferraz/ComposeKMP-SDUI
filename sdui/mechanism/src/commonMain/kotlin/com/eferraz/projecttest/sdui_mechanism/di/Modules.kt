package com.eferraz.projecttest.sdui_mechanism.di

import com.eferraz.projecttest.sdui_mechanism.configurePolymorphism
import com.eferraz.projecttest.sdui_mechanism.models.UIAction
import com.eferraz.projecttest.sdui_mechanism.models.UIComponent
import kotlinx.serialization.json.Json
import kotlinx.serialization.modules.SerializersModule
import org.koin.dsl.module

val mechanismModule = module {
    factory {
        Json {
            serializersModule = SerializersModule {
                configurePolymorphism<UIComponent>(this)
                configurePolymorphism<UIAction>(this)
            }
        }
    }
}