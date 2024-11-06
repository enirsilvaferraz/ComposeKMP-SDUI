package com.eferraz.projecttest.sdui_mechanism

import kotlinx.serialization.ExperimentalSerializationApi
import kotlinx.serialization.InternalSerializationApi
import kotlinx.serialization.serializer

@OptIn(ExperimentalSerializationApi::class, InternalSerializationApi::class, InternalSerializationApi::class)
inline fun <reified Element : UIElement> Element.serial(): String = this::class.serializer().descriptor.serialName

@OptIn(ExperimentalSerializationApi::class, InternalSerializationApi::class)
inline fun <reified Element : UIAction> Element.serial(): String = this::class.serializer().descriptor.serialName
