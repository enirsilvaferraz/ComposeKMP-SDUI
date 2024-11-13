package com.eferraz.projecttest.sdui_domain

import androidx.compose.ui.Alignment
import com.eferraz.projecttest.sdui_mechanism.models.UIAction
import com.eferraz.projecttest.sdui_mechanism.models.UIComponent
import com.eferraz.projecttest.sdui_mechanism.models.UIModifier
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
@SerialName("ui-component-scaffold")
data class UIScaffold(
    override val modifier: List<UIModifier> = listOf(),
    val topBar: UIComponent? = null,
    val content: UIComponent,
    val bottomBar: UIComponent? = null,
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
@SerialName("ui-component-bottom-bar")
data class UIBottomBar(
    override val modifier: List<UIModifier> = listOf(),
    val content: List<UIBottomNavigationItem>,
) : UIComponent()

@Serializable
@SerialName("ui-component-bottom-navigation-item")
data class UIBottomNavigationItem(
    override val modifier: List<UIModifier> = listOf(),
    val icon: UIIcon, val label: UIComponent, val onClick: UIAction
) : UIComponent()

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

@Serializable
@SerialName("ui-component-nav-host")
data class UINavHost(
    override val modifier: List<UIModifier> = listOf(),
    val pages: List<Pages>,
) : UIComponent() {

    @Serializable
    @SerialName("ui-component-nav-host-pages")
    data class Pages(
        override val modifier: List<UIModifier> = listOf(),
        val route: String,
        val pages: UIComponent,
    ) : UIComponent()
}





