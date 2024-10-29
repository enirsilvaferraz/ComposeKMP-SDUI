package com.eferraz.projecttest.frontend.core

import androidx.compose.runtime.*
import com.eferraz.projecttest.frontend.SDUIContainerScope
import kotlinx.serialization.ExperimentalSerializationApi
import kotlinx.serialization.InternalSerializationApi
import kotlinx.serialization.serializer
import org.koin.compose.koinInject
import org.koin.core.component.get
import org.koin.core.qualifier.named

@OptIn(ExperimentalSerializationApi::class, InternalSerializationApi::class)
inline fun <reified Element : UIElement> Element.serial(): String = this::class.serializer().descriptor.serialName

@OptIn(ExperimentalSerializationApi::class, InternalSerializationApi::class)
inline fun <reified Element : UIAction> Element.serial(): String = this::class.serializer().descriptor.serialName
