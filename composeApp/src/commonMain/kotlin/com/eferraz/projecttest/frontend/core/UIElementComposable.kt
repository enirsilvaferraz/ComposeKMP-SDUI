package com.eferraz.projecttest.frontend.core

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Settings
import androidx.compose.runtime.Composable
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
            bottomBar = {component.bottonBar?.build()}
        )
    }
}

internal class UILazyColumnComposable : UIElementComposable<UILazyColumn>() {

    @Composable
    override fun build(modifier: Modifier, component: UILazyColumn) {

        LazyColumn(
            modifier = modifier,
            content = {
                items(component.body) {
                    it.build()
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
            Icon(imageVector = it, contentDescription = component.contentDescription)
        }
    }
}

internal class UIBottomBarComposable : UIElementComposable<UIBottomBar>() {

    @Composable
    override fun build(modifier: Modifier, component: UIBottomBar) {
        BottomAppBar {
            component.content.fastForEach {
                it.build()
            }
        }
    }
}

internal class UIBOttomBarItemComposable: UIElementComposable<UIBottomBarItem>() {

    @Composable
    override fun build(modifier: Modifier, component: UIBottomBarItem) {
        Row{
            BottomNavigationItem(
                selected = false,
                icon = {component.icon.build()},
                label = {component.label.build()},
                onClick = {}
            )
        }
    }
}
