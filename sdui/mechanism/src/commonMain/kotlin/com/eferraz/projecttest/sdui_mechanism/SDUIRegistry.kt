package com.eferraz.projecttest.sdui_mechanism


import kotlinx.serialization.ExperimentalSerializationApi
import kotlinx.serialization.InternalSerializationApi
import kotlinx.serialization.KSerializer
import kotlinx.serialization.modules.SerializersModule
import kotlinx.serialization.serializer
import org.koin.core.module.Module
import org.koin.core.qualifier.named
import org.koin.core.scope.Scope
import org.koin.dsl.bind
import kotlin.reflect.KClass

@OptIn(InternalSerializationApi::class, ExperimentalSerializationApi::class)
internal inline fun <reified Element : UIElement> Module.registerComponent(
    clazz: KClass<Element>,
    composable: UIElementComposable<Element>
) {

    val name = clazz.serializer().descriptor.serialName
    factory(named(name)) { composable } bind UIElementComposable::class
    factory(named(name)) { SDUIElementRegister(actual = clazz, serializer = clazz.serializer()) }
}

@OptIn(InternalSerializationApi::class, ExperimentalSerializationApi::class)
internal inline fun <reified Action : UIAction> Module.registerComponent(
    clazz: KClass<Action>,
    composable: UIActionBehavior<Action>
) {

    val name = clazz.serializer().descriptor.serialName
    factory(named(name)) { composable } bind UIActionBehavior::class
    factory(named(name)) { SDUIActionRegister(actual = clazz, serializer = clazz.serializer()) }
}

internal data class SDUIElementRegister<T : UIElement>(val actual: KClass<T>, val serializer: KSerializer<T>)
internal data class SDUIActionRegister<T : UIAction>(val actual: KClass<T>, val serializer: KSerializer<T>)

fun Scope.sduiPolymorphicComponents() = SerializersModule {
    getAll<SDUIElementRegister<UIElement>>().forEach {
        polymorphic(UIElement::class, it.actual, it.serializer)
    }
    getAll<SDUIActionRegister<UIAction>>().forEach {
        polymorphic(UIAction::class, it.actual, it.serializer)
    }
}