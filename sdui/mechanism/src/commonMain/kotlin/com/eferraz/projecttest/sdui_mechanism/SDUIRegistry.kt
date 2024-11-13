package com.eferraz.projecttest.sdui_mechanism


import com.eferraz.projecttest.sdui_mechanism.models.UIAction
import com.eferraz.projecttest.sdui_mechanism.models.UIActionImpl
import com.eferraz.projecttest.sdui_mechanism.models.UIComponent
import com.eferraz.projecttest.sdui_mechanism.models.UIComponentImpl
import com.eferraz.projecttest.sdui_mechanism.models.UIElement
import com.eferraz.projecttest.sdui_mechanism.models.UIModifier
import com.eferraz.projecttest.sdui_mechanism.models.UIModifierImpl
import com.eferraz.projecttest.sdui_mechanism.models.UIScope
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
 * Usado para registrar um elemento do tipo [UIComponent] e sua respectiva implementação na injeção de dependências.
 *
 * @param impl A implementação do elemento a ser registrada.
 */
@OptIn(InternalSerializationApi::class, ExperimentalSerializationApi::class)
inline fun <reified Element : UIComponent, reified Scope: UIScope> Module.registerComponent(impl: UIComponentImpl<Element, Scope>) {

    val name = Element::class.serializer().descriptor.serialName
    factory(named(name)) { impl } bind UIComponentImpl::class
    factory(named(name)) { SDUIPolymorphicRegister( base = UIComponent::class, sub = Element::class) }
}

/**
 * Usado para registrar um elemento do tipo [UIAction] e sua respectiva implementação na injeção de dependências.
 *
 * @param impl A implementação do elemento a ser registrada.
 */
@OptIn(InternalSerializationApi::class, ExperimentalSerializationApi::class)
inline fun <reified Element : UIAction, reified Scope: UIScope> Module.registerAction(impl: UIActionImpl<Element, Scope>) {

    val name = Element::class.serializer().descriptor.serialName
    factory(named(name)) { impl } bind UIActionImpl::class
    factory(named(name)) { SDUIPolymorphicRegister(base = UIAction::class, sub = Element::class) }
}

/**
 * Usado para registrar um elemento do tipo [UIModifier] e sua respectiva implementação na injeção de dependências.
 *
 * @param impl A implementação do elemento a ser registrada.
 */
@OptIn(InternalSerializationApi::class, ExperimentalSerializationApi::class)
inline fun <reified Element : UIModifier> Module.registerModifier(impl: UIModifierImpl<Element>) {

    val name = Element::class.serializer().descriptor.serialName
    factory(named(name)) { impl } bind UIModifierImpl::class
    factory(named(name)) { SDUIPolymorphicRegister(base= UIModifier::class, sub = Element::class) }
}


/**
 * Data class de registro das [UIElement]
 */
data class SDUIPolymorphicRegister<Base: UIElement, Sub: Base>(val base: KClass<Base>, val sub: KClass<Sub>) {

    @OptIn(InternalSerializationApi::class)
    fun SerializersModuleBuilder.registerPolymorphism() = polymorphic(base, sub, sub.serializer())
}

/**
 * Usado para recuperar as [UIElement] registradas na injeção de dependências e adicionar ao [SerializersModule] do Kotlin Serialization.
 */
inline fun Scope.configurePolymorphism(scope: SerializersModuleBuilder) {
    getAll<SDUIPolymorphicRegister<*,*>>().forEach {
        with(it) {
            scope.registerPolymorphism()
        }
    }
}