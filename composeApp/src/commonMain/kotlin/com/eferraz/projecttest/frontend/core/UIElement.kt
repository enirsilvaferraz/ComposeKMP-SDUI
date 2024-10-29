package com.eferraz.projecttest.frontend.core

import com.eferraz.projecttest.frontend.NavRoot
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
abstract class UIElement

@Serializable
@SerialName("ui-scafold")
data class UIScaffold(val topBar: UIElement? = null, val content: UIElement, val bottonBar: UIElement? = null) :
    UIElement()

@Serializable
@SerialName("ui-top-bar")
data class UITopBar(val title: UIElement) : UIElement()

@Serializable
@SerialName("ui-lazy-column")
data class UILazyColumn(val body: List<UIElement>) : UIElement()

@Serializable
@SerialName("ui-lazy-column-item")
data class UILazyColumnItem(val item: UIElement) : UIElement()

@Serializable
@SerialName("ui-text")
data class UIText(val text: String) : UIElement()

@Serializable
@SerialName("ui-icon")
data class UIIcon(val icon: String, val contentDescription: String = "") : UIElement()

@Serializable
@SerialName("ui-botton-bar")
data class UIBottomBar(val content: List<Item>) : UIElement() {

    @Serializable
    data class Item(val icon: UIIcon, val label: UIElement, val onClick: UIAction)
}

@Serializable
@SerialName("ui-horizontal-pager")
data class UIHorizontalPager(val pages: List<UIElement>) : UIElement()


