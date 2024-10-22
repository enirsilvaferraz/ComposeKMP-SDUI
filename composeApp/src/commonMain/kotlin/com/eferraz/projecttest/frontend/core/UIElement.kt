package com.eferraz.projecttest.frontend.core

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
abstract class UIElement

@Serializable
@SerialName("ui-scafold")
data class UIScafold(val body: List<UIElement>) : UIElement()

@Serializable
@SerialName("ui-text")
data class UIText(val text: String) : UIElement()

