package com.eferraz.projecttest.sdui_components

import androidx.compose.ui.Alignment
import com.eferraz.projecttest.sdui_mechanism.UIAction
import com.eferraz.projecttest.sdui_mechanism.UIElement
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

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


@Serializable
@SerialName("ui-row")
data class UIRow(
    val verticalAlignment: VerticalAlignment,
    val content: List<UIElement>
) : UIElement() {

    enum class VerticalAlignment(val alignment: Alignment.Vertical) {
        TOP (Alignment.Top),
        CENTER_VERTICALLY (Alignment.CenterVertically),
        BOTTOM (Alignment.Bottom);
    }
}

@Serializable
@SerialName("ui-image")
data class UIImage(val url: String, val contentDescription: String) : UIElement()





