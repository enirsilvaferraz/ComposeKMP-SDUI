package com.eferraz.projecttest.frontend.di

import com.eferraz.projecttest.frontend.core.UIElement
import com.eferraz.projecttest.frontend.core.UIScafold
import com.eferraz.projecttest.frontend.core.UIText
import com.eferraz.projecttest.frontend.core.SDUIViewModel
import com.eferraz.projecttest.frontend.core.UIElementComposable
import com.eferraz.projecttest.frontend.core.UIScafoldComposable
import com.eferraz.projecttest.frontend.core.UITextComposable
import com.eferraz.projecttest.frontend.core.GetScreenUseCase
import com.eferraz.projecttest.frontend.core.ScreenRepository
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
    factoryOf(::GetScreenUseCase)
    factoryOf(::ScreenRepository)

    registerComponent(UIScafold::class, UIScafoldComposable())
    registerComponent(UIText::class, UITextComposable())

    factory { Json { serializersModule = sduiPolymorphicComponents() } }
}

@OptIn(InternalSerializationApi::class, ExperimentalSerializationApi::class)
private inline fun <reified T : UIElement> Module.registerComponent(
    clazz: KClass<T>,
    composable: UIElementComposable<T>
) {

    val name = clazz.serializer().descriptor.serialName
    factory(named(name)) { composable } bind UIElementComposable::class
    factory(named(name)) { SDUIComponentRegister(actual = clazz, serializer = clazz.serializer()) }
}

private data class SDUIComponentRegister<T : UIElement>(val actual: KClass<T>, val serializer: KSerializer<T>)

private fun Scope.sduiPolymorphicComponents() = SerializersModule {
    getAll<SDUIComponentRegister<UIElement>>().forEach {
        polymorphic(UIElement::class, it.actual, it.serializer)
    }
}