package com.eferraz.projecttest.frontend.core

import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Settings
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.util.fastForEach

internal abstract class UIElementComposable<Element : UIElement> {

    @Composable
    abstract fun build(modifier: Modifier = Modifier, component: Element)
}

internal class UIScaffoldComposable : UIElementComposable<UIScaffold>() {

    @Composable
    override fun build(modifier: Modifier, component: UIScaffold) {

        Scaffold(
            modifier = modifier,
            topBar = { component.topBar?.build() },
            content = { component.content.build() },
            bottomBar = { component.bottonBar?.build() }
        )
    }
}

internal class UILazyColumnComposable : UIElementComposable<UILazyColumn>() {

    @Composable
    override fun build(modifier: Modifier, component: UILazyColumn) {

        LazyColumn(
            modifier = modifier,
            content = {
                component.body.fastForEach {
                    item {
                        it.build()
                    }
                }
            }
        )
    }
}

internal class UITopBarComposable : UIElementComposable<UITopBar>() {

    @Composable
    override fun build(modifier: Modifier, component: UITopBar) {

        TopAppBar(
            modifier = modifier,
            title = { component.title.build() }
        )
    }
}

internal class UITextComposable : UIElementComposable<UIText>() {

    @Composable
    override fun build(modifier: Modifier, component: UIText) {

        Text(
            modifier = modifier,
            text = component.text
        )
    }
}

internal class UIIconComposable : UIElementComposable<UIIcon>() {

    @Composable
    override fun build(modifier: Modifier, component: UIIcon) {

        when (component.icon) {
            Icons.Default.Home.name -> Icons.Default.Home
            Icons.Default.Settings.name -> Icons.Default.Settings
            else -> null
        }?.let {

            Icon(
                modifier = modifier,
                imageVector = it,
                contentDescription = component.contentDescription
            )
        }
    }
}

internal class UIBottomBarComposable : UIElementComposable<UIBottomBar>() {

    @Composable
    override fun build(modifier: Modifier, component: UIBottomBar) {

        val (selected, setSelected) = remember { mutableStateOf(component.content.first()) }

        BottomAppBar(
            modifier = modifier
        ) {
            component.content.fastForEach {
                BottomNavigationItem(
                    selected = it == selected,
                    icon = { it.icon.build() },
                    label = { it.label.build() },
                    onClick = { setSelected(it) }
                )
            }
        }
    }
}