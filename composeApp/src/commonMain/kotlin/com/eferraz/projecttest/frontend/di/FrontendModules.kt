package com.eferraz.projecttest.frontend.di

import com.eferraz.projecttest.frontend.core.*
import com.eferraz.projecttest.frontend.core.GetScreenUseCase
import com.eferraz.projecttest.frontend.core.SDUIViewModel
import com.eferraz.projecttest.frontend.core.ScreenRepository
import com.eferraz.projecttest.frontend.core.UIElementComposable
import com.eferraz.projecttest.frontend.core.UIScaffoldComposable
import com.eferraz.projecttest.frontend.core.UITextComposable
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

    factory { Json { serializersModule = sduiPolymorphicComponents() } }

    viewModelOf(::SDUIViewModel)
    factoryOf(::GetScreenUseCase)
    factoryOf(::ScreenRepository)

    registerComponent(UIScaffold::class, UIScaffoldComposable())
    registerComponent(UIText::class, UITextComposable())
    registerComponent(UITopBar::class, UITopBarComposable())
    registerComponent(UILazyColumn::class, UILazyColumnComposable())
    registerComponent(UIIcon::class, UIIconComposable())
    registerComponent(UIBottomBar::class, UIBottomBarComposable())
    registerComponent(UIHorizontalPager::class, UIHorizontalPagerComposable())

    registerComponent(UINavigation::class, UINavigationBehavior())
    registerComponent(UIChangePage::class, UIChangePageBehavior())
}

@OptIn(InternalSerializationApi::class, ExperimentalSerializationApi::class)
private inline fun <reified Element : UIElement> Module.registerComponent(
    clazz: KClass<Element>,
    composable: UIElementComposable<Element>
) {

    val name = clazz.serializer().descriptor.serialName
    factory(named(name)) { composable } bind UIElementComposable::class
    factory(named(name)) { SDUIElementRegister(actual = clazz, serializer = clazz.serializer()) }
}

@OptIn(InternalSerializationApi::class, ExperimentalSerializationApi::class)
private inline fun <reified Action : UIAction> Module.registerComponent(
    clazz: KClass<Action>,
    composable: UIActionBehavior<Action>
) {

    val name = clazz.serializer().descriptor.serialName
    factory(named(name)) { composable } bind UIActionBehavior::class
    factory(named(name)) { SDUIActionRegister(actual = clazz, serializer = clazz.serializer()) }
}

private data class SDUIElementRegister<T : UIElement>(val actual: KClass<T>, val serializer: KSerializer<T>)
private data class SDUIActionRegister<T : UIAction>(val actual: KClass<T>, val serializer: KSerializer<T>)

private fun Scope.sduiPolymorphicComponents() = SerializersModule {
    getAll<SDUIElementRegister<UIElement>>().forEach {
        polymorphic(UIElement::class, it.actual, it.serializer)
    }
    getAll<SDUIActionRegister<UIAction>>().forEach {
        polymorphic(UIAction::class, it.actual, it.serializer)
    }
}