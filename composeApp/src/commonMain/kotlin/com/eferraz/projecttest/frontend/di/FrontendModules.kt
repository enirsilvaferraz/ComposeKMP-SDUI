package com.eferraz.projecttest.frontend.di

import com.eferraz.projecttest.backend.api.SDUIComponent
import com.eferraz.projecttest.backend.api.SDUIScreen
import com.eferraz.projecttest.backend.api.SDUIText
import com.eferraz.projecttest.frontend.SDUIComponentComposable
import com.eferraz.projecttest.frontend.SDUIScreenComposable
import com.eferraz.projecttest.frontend.SDUITextComposable
import com.eferraz.projecttest.frontend.SDUIViewModel
import kotlinx.serialization.ExperimentalSerializationApi
import kotlinx.serialization.InternalSerializationApi
import kotlinx.serialization.KSerializer
import kotlinx.serialization.json.Json
import kotlinx.serialization.modules.SerializersModule
import kotlinx.serialization.serializer
import org.koin.core.module.Module
import org.koin.core.module.dsl.factoryOf
import org.koin.core.module.dsl.viewModelOf
import org.koin.core.qualifier.named
import org.koin.core.scope.Scope
import org.koin.dsl.bind
import org.koin.dsl.module
import kotlin.reflect.KClass


val frontendModule = module {

    viewModelOf(::SDUIViewModel)

    registerComponent(SDUIScreen::class, SDUIScreenComposable())
    registerComponent(SDUIText::class, SDUITextComposable())

    factory { Json { serializersModule = sduiPolymorphicComponents() } }
}

@OptIn(InternalSerializationApi::class, ExperimentalSerializationApi::class)
private inline fun <reified T : SDUIComponent> Module.registerComponent(
    clazz: KClass<T>,
    composable: SDUIComponentComposable<T>
) {

    val name = clazz.serializer().descriptor.serialName
    factory(named(name)) { composable } bind SDUIComponentComposable::class
    factory(named(name)) { SDUIComponentRegister(actual = clazz, serializer = clazz.serializer()) }
}

private data class SDUIComponentRegister<T : SDUIComponent>(val actual: KClass<T>, val serializer: KSerializer<T>)

private fun Scope.sduiPolymorphicComponents() = SerializersModule {
    getAll<SDUIComponentRegister<SDUIComponent>>().forEach {
        polymorphic(SDUIComponent::class, it.actual, it.serializer)
    }
}