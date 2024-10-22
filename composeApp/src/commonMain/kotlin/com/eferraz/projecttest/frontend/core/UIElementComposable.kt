package com.eferraz.projecttest.frontend.core

import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Text
import androidx.compose.runtime.Composable

internal abstract class UIElementComposable<T : UIElement> {

    @Composable
    abstract fun build(component: T)
}

internal class UIScafoldComposable : UIElementComposable<UIScafold>() {

    @Composable
    override fun build(component: UIScafold) {

        Text(component.serial())

        LazyColumn {

            items(component.body) {
                it.build()
            }
        }
    }
}

internal class UITextComposable : UIElementComposable<UIText>() {

    @Composable
    override fun build(component: UIText) {
        Text(component.text)
    }
}