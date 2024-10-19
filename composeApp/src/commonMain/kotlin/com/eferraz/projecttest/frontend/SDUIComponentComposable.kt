package com.eferraz.projecttest.frontend

import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import com.eferraz.projecttest.backend.api.SDUIComponent
import com.eferraz.projecttest.backend.api.SDUIScreen
import com.eferraz.projecttest.backend.api.SDUIText
import org.koin.compose.koinInject
import org.koin.core.qualifier.named

internal abstract class SDUIComponentComposable<T : SDUIComponent> {

    @Composable
    abstract fun build(component: T)
}

internal class SDUIScreenComposable : SDUIComponentComposable<SDUIScreen>() {

    @Composable
    override fun build(component: SDUIScreen) {

        Text(component.getSerialName())

        LazyColumn {

            items(component.body) {
                koinInject<SDUIComponentComposable<SDUIComponent>>(named(it.getSerialName())).build(it)
            }
        }
    }
}

internal class SDUITextComposable : SDUIComponentComposable<SDUIText>() {

    @Composable
    override fun build(component: SDUIText) {
        Text(component.text)
    }
}