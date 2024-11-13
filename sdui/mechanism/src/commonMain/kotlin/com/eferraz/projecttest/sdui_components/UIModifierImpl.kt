package com.eferraz.projecttest.sdui_components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.eferraz.projecttest.sdui_domain.UIBackground
import com.eferraz.projecttest.sdui_domain.UIFillMaxWidth
import com.eferraz.projecttest.sdui_domain.UIPadding
import com.eferraz.projecttest.sdui_domain.UISize
import com.eferraz.projecttest.sdui_mechanism.models.UIModifierImpl

internal class UIPaddingImpl : UIModifierImpl<UIPadding>() {

    override fun Modifier.build(modifier: UIPadding) =
        padding(start = modifier.start.dp, top = modifier.top.dp, end = modifier.end.dp, bottom = modifier.bottom.dp)
}

internal class UISizeImpl : UIModifierImpl<UISize>() {

    override fun Modifier.build(modifier: UISize) =
        size(width = modifier.width.dp, height = modifier.height.dp)
}

internal class UIBackgroundImpl : UIModifierImpl<UIBackground>() {

    override fun Modifier.build(modifier: UIBackground) =
        background(color = Color(modifier.color))
}

internal class UIFillMaxWidthImpl : UIModifierImpl<UIFillMaxWidth>() {

    override fun Modifier.build(modifier: UIFillMaxWidth) =
        fillMaxWidth()
}

