package com.eferraz.projecttest.backend.api

import kotlinx.serialization.KSerializer
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
abstract class SDUIComponent {

    @OptIn(kotlinx.serialization.ExperimentalSerializationApi::class)
    fun getSerialName() = getSerializer().descriptor.serialName
    abstract fun getSerializer() : KSerializer<out SDUIComponent>
}

@Serializable
@SerialName("sdui-screen")
data class SDUIScreen(val body: List<SDUIComponent>) : SDUIComponent() {
    override fun getSerializer() = serializer()
}

@Serializable
@SerialName("sdui-text")
data class SDUIText(val text: String) : SDUIComponent() {
    override fun getSerializer() = serializer()
}