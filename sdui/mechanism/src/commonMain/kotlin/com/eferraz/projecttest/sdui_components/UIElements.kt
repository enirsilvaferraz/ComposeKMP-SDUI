package com.eferraz.projecttest.sdui_components

import androidx.compose.ui.Alignment
import com.eferraz.projecttest.sdui_mechanism.models.UIAction
import com.eferraz.projecttest.sdui_mechanism.models.UIComponent
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
@SerialName("ui-scafold")
data class UIScaffold(val topBar: UIComponent? = null, val content: UIComponent, val bottonBar: UIComponent? = null) :
    UIComponent()

@Serializable
@SerialName("ui-top-bar")
data class UITopBar(val title: UIComponent) : UIComponent()

@Serializable
@SerialName("ui-lazy-column")
data class UILazyColumn(val body: List<UIComponent>) : UIComponent()

@Serializable
@SerialName("ui-lazy-column-item")
data class UILazyColumnItem(val item: UIComponent) : UIComponent()

@Serializable
@SerialName("ui-text")
data class UIText(val text: String) : UIComponent()

@Serializable
@SerialName("ui-icon")
data class UIIcon(val icon: String, val contentDescription: String = "") : UIComponent()

@Serializable
@SerialName("ui-botton-bar")
data class UIBottomBar(val content: List<Item>) : UIComponent() {

    @Serializable
    data class Item(val icon: UIIcon, val label: UIComponent, val onClick: UIAction)
}

@Serializable
@SerialName("ui-horizontal-pager")
data class UIHorizontalPager(val pages: List<UIComponent>) : UIComponent()


@Serializable
@SerialName("ui-row")
data class UIRow(
    val verticalAlignment: VerticalAlignment,
    val content: List<UIComponent>
) : UIComponent() {

    enum class VerticalAlignment(val alignment: Alignment.Vertical) {
        TOP(Alignment.Top),
        CENTER_VERTICALLY(Alignment.CenterVertically),
        BOTTOM(Alignment.Bottom);
    }
}

@Serializable
@SerialName("ui-image")
data class UIImage(val url: String, val contentDescription: String) : UIComponent()





