package com.eferraz.projecttest.sdui_components

import androidx.compose.ui.Alignment
import com.eferraz.projecttest.sdui_mechanism.models.UIAction
import com.eferraz.projecttest.sdui_mechanism.models.UIComponent
import com.eferraz.projecttest.sdui_mechanism.models.UIModifier
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
@SerialName("ui-component-scafold")
data class UIScaffold(
    override val modifier: List<UIModifier> = listOf(),
    val topBar: UIComponent? = null,
    val content: UIComponent,
    val bottonBar: UIComponent? = null,
) : UIComponent()

@Serializable
@SerialName("ui-component-top-bar")
data class UITopBar(
    override val modifier: List<UIModifier> = listOf(),
    val title: UIComponent,
) : UIComponent()

@Serializable
@SerialName("ui-component-lazy-column")
data class UILazyColumn(
    override val modifier: List<UIModifier> = listOf(),
    val body: List<UIComponent>,
) : UIComponent()

@Serializable
@SerialName("ui-component-lazy-column-item")
data class UILazyColumnItem(
    override val modifier: List<UIModifier> = listOf(),
    val item: UIComponent,
) : UIComponent()

@Serializable
@SerialName("ui-component-text")
data class UIText(
    override val modifier: List<UIModifier> = listOf(),
    val text: String,
) : UIComponent()

@Serializable
@SerialName("ui-component-icon")
data class UIIcon(
    override val modifier: List<UIModifier> = listOf(),
    val icon: String, val contentDescription: String = "",
) : UIComponent()

@Serializable
@SerialName("ui-component-botton-bar")
data class UIBottomBar(
    override val modifier: List<UIModifier> = listOf(),
    val content: List<Item>,
) : UIComponent() {

    @Serializable
    data class Item(val icon: UIIcon, val label: UIComponent, val onClick: UIAction)
}

@Serializable
@SerialName("ui-component-horizontal-pager")
data class UIHorizontalPager(
    override val modifier: List<UIModifier> = listOf(),
    val pages: List<UIComponent>,
) : UIComponent()


@Serializable
@SerialName("ui-component-row")
data class UIRow(
    override val modifier: List<UIModifier> = listOf(),
    val verticalAlignment: VerticalAlignment,
    val content: List<UIComponent>,
) : UIComponent() {

    enum class VerticalAlignment(val alignment: Alignment.Vertical) {
        TOP(Alignment.Top),
        CENTER_VERTICALLY(Alignment.CenterVertically),
        BOTTOM(Alignment.Bottom);
    }
}

@Serializable
@SerialName("ui-component-image")
data class UIImage(
    override val modifier: List<UIModifier> = listOf(),
    val url: String?,
    val contentDescription: String,
) : UIComponent()





