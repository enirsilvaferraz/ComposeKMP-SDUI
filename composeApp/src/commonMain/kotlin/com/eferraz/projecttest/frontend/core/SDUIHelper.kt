package com.eferraz.projecttest.frontend.core

import androidx.compose.runtime.Composable
import kotlinx.serialization.*
import org.koin.compose.koinInject
import org.koin.core.qualifier.named

@OptIn(ExperimentalSerializationApi::class, InternalSerializationApi::class)
inline fun <reified T: UIElement> T.serial() : String = this::class.serializer().descriptor.serialName

@Composable
internal inline fun <reified T : UIElement> T.build() = koinInject<UIElementComposable<UIElement>>(named(this.serial())).build(component =this)