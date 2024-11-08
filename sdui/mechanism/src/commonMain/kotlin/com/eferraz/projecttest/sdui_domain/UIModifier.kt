package com.eferraz.projecttest.sdui_domain

import com.eferraz.projecttest.sdui_mechanism.models.UIModifierAbs
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

/**
 * Represents the base configuration of [androidx.compose.ui.Modifier].
 */
@Serializable
sealed class UIModifier : UIModifierAbs

/**
 * Represents the configuration [androidx.compose.foundation.layout.padding] of [androidx.compose.ui.Modifier].
 */
@Serializable
@SerialName("ui-modifier-padding")
data class UIPadding(
    val top: Int = 0,
    val bottom: Int = 0,
    val start: Int = 0,
    val end: Int = 0,
) : UIModifier() {
    constructor(all: Int) : this(top = all, bottom = all, start = all, end = all)
    constructor(horizontal: Int = 0, vertical: Int = 0) : this(top = vertical, bottom = vertical, start = horizontal, end = horizontal)
}

/**
 * Represents the configuration [androidx.compose.foundation.layout.size] of [androidx.compose.ui.Modifier].
 */
@Serializable
@SerialName("ui-modifier-size")
data class UISize(
    val width: Int = 0,
    val height: Int = 0,
) : UIModifier() {
    constructor(size: Int) : this(width = size, height = size)
}

/**
 * Represents the configuration [androidx.compose.foundation.layout.fillMaxWidth] of [androidx.compose.ui.Modifier].
 */
@Serializable
@SerialName("ui-modifier-fill-max-width")
data object UIFillMaxWidth : UIModifier()

/**
 * Represents the configuration [androidx.compose.foundation.background] of [androidx.compose.ui.Modifier].
 */
@Serializable
@SerialName("ui-modifier-background")
data class UIBackground(
    val color: Long,
) : UIModifier()
