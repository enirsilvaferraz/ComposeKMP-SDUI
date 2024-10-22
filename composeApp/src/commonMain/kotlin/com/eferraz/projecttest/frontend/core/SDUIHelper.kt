package com.eferraz.projecttest.frontend.core

import androidx.compose.runtime.Composable
import kotlinx.serialization.ExperimentalSerializationApi
import kotlinx.serialization.InternalSerializationApi
import kotlinx.serialization.serializer
import org.koin.compose.koinInject
import org.koin.core.qualifier.named

@OptIn(ExperimentalSerializationApi::class, InternalSerializationApi::class)
inline fun <reified Element : UIElement> Element.serial(): String = this::class.serializer().descriptor.serialName

@Composable
internal inline fun <reified Element : UIElement> Element.build() {
    with(koinInject<UIElementComposable<Element>>(named(this.serial()))) {
        build(component = this@build)
    }
}