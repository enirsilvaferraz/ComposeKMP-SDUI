package com.eferraz.projecttest.sdui_mechanism


import com.eferraz.projecttest.sdui_mechanism.models.UIElement
import com.eferraz.projecttest.sdui_mechanism.models.UIElementImpl
import kotlinx.serialization.ExperimentalSerializationApi
import kotlinx.serialization.InternalSerializationApi
import kotlinx.serialization.modules.SerializersModule
import kotlinx.serialization.modules.SerializersModuleBuilder
import kotlinx.serialization.serializer
import org.koin.core.module.Module
import org.koin.core.qualifier.named
import org.koin.core.scope.Scope
import org.koin.dsl.bind
import kotlin.reflect.KClass

/**
 * Usado para registrar um elemento do tipo [UIElement] e sua respectiva implementação na injeção de dependências.
 *
 * @param uiImplementation A implementação do elemento a ser registrada.
 */
@OptIn(InternalSerializationApi::class, ExperimentalSerializationApi::class)
internal inline fun <reified Element : UIElement> Module.registerElement(uiImplementation: UIElementImpl<Element>) {

    val name = Element::class.serializer().descriptor.serialName
    factory(named(name)) { uiImplementation } bind UIElementImpl::class
    factory(named(name)) { SDUIPolymorphicRegister(actual = Element::class) }
}

/**
 * Data class de registro das [UIElement]
 */
data class SDUIPolymorphicRegister<Element : UIElement>(val actual: KClass<Element>)

/**
 * Usado para recuperar as [UIElement] registradas na injeção de dependências e adicionar ao [SerializersModule] do Kotlin Serialization.
 */
@OptIn(InternalSerializationApi::class)
inline fun <reified Element : UIElement> Scope.configurePolymorphism(scope: SerializersModuleBuilder) {
    getAll<SDUIPolymorphicRegister<Element>>().forEach {
        scope.polymorphic(Element::class, it.actual, it.actual.serializer())
    }
}