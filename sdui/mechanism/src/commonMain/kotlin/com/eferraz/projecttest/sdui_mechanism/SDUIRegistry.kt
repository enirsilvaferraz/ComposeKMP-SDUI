package com.eferraz.projecttest.sdui_mechanism


import com.eferraz.projecttest.sdui_mechanism.models.UIAction
import com.eferraz.projecttest.sdui_mechanism.models.UIComponent
import com.eferraz.projecttest.sdui_mechanism.models.UIElement
import com.eferraz.projecttest.sdui_mechanism.models.UIElementImpl
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
 * Usado para registrar um elemento do tipo [UIElement] e sua respectiva implementação na injeção de dependências.
 *
 * @param elementKClass A classe do elemento a ser registrado.
 * @param impl A implementação do elemento a ser registrada.
 */
@OptIn(InternalSerializationApi::class, ExperimentalSerializationApi::class)
internal inline fun <reified Element : UIElement> Module.registerElement(elementKClass: KClass<Element>, impl: UIElementImpl<Element>) {

    val name = elementKClass.serializer().descriptor.serialName
    factory(named(name)) { impl } bind UIElementImpl::class
    factory(named(name)) { SDUIPolymorphicRegister(actual = elementKClass, serializer = elementKClass.serializer()) }
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