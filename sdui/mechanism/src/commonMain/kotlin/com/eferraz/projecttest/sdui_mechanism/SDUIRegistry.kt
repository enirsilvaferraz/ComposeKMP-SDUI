package com.eferraz.projecttest.sdui_mechanism


import com.eferraz.projecttest.sdui_mechanism.models.UIAction
import com.eferraz.projecttest.sdui_mechanism.models.UIActionBehavior
import com.eferraz.projecttest.sdui_mechanism.models.UIComponent
import com.eferraz.projecttest.sdui_mechanism.models.UIElement
import com.eferraz.projecttest.sdui_mechanism.models.UIElementComposable
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

/**
 * Usado para registrar um elemento do tipo [UIComponent] e sua respectiva implementação na injeção de dependências.
 *
 * @param clazz A classe do elemento a ser registrado.
 * @param composable A implementação do elemento a ser registrada.
 */
@OptIn(InternalSerializationApi::class, ExperimentalSerializationApi::class)
internal inline fun <reified Element : UIComponent> Module.registerComponent(clazz: KClass<Element>, composable: UIElementComposable<Element>) {

    val name = clazz.serializer().descriptor.serialName
    factory(named(name)) { composable } bind UIElementComposable::class
    factory(named(name)) { SDUIPolymorphicRegister(actual = clazz, serializer = clazz.serializer()) }
}

/**
 * Usado para registrar um elemento do tipo [UIAction] e sua respectiva implementação na injeção de dependências.
 *
 * @param clazz A classe do elemento a ser registrado.
 * @param composable A implementação do elemento a ser registrada.
 */
@OptIn(InternalSerializationApi::class, ExperimentalSerializationApi::class)
internal inline fun <reified Action : UIAction> Module.registerAction(clazz: KClass<Action>, composable: UIActionBehavior<Action>) {

    val name = clazz.serializer().descriptor.serialName
    factory(named(name)) { composable } bind UIActionBehavior::class
    factory(named(name)) { SDUIPolymorphicRegister(actual = clazz, serializer = clazz.serializer()) }
}

/**
 * Data class de registro das [UIElement]
 */
internal data class SDUIPolymorphicRegister<T : UIElement>(val actual: KClass<T>, val serializer: KSerializer<T>)

/**
 * Usado para recuperar as [UIElement] registradas na injeção de dependências e adicionar ao [SerializersModule] do Kotlin Serialization.
 */
internal fun Scope.sduiPolymorphicComponents() = SerializersModule {

    getAll<SDUIPolymorphicRegister<UIComponent>>().forEach {
        polymorphic(UIComponent::class, it.actual, it.serializer)
    }

    getAll<SDUIPolymorphicRegister<UIAction>>().forEach {
        polymorphic(UIAction::class, it.actual, it.serializer)
    }
}