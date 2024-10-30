package com.eferraz.projecttest.sdui_components

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Settings
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.util.fastForEach
import com.eferraz.projecttest.sdui_mechanism.SDUIContainerScope
import com.eferraz.projecttest.sdui_mechanism.UIElementComposable

internal class UIScaffoldComposable : UIElementComposable<UIScaffold>() {

    @Composable
    override fun SDUIContainerScope.build(modifier: Modifier, component: UIScaffold) {

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
    override fun SDUIContainerScope.build(modifier: Modifier, component: UILazyColumn) {

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
    override fun SDUIContainerScope.build(modifier: Modifier, component: UITopBar) {

        TopAppBar(
            modifier = modifier,
            title = { component.title.build() }
        )
    }
}

internal class UITextComposable : UIElementComposable<UIText>() {

    @Composable
    override fun SDUIContainerScope.build(modifier: Modifier, component: UIText) {

        Text(
            modifier = modifier,
            text = component.text
        )
    }
}

internal class UIIconComposable : UIElementComposable<UIIcon>() {

    @Composable
    override fun SDUIContainerScope.build(modifier: Modifier, component: UIIcon) {

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

    @OptIn(ExperimentalFoundationApi::class)
    @Composable
    override fun SDUIContainerScope.build(modifier: Modifier, component: UIBottomBar) {

        BottomAppBar(modifier = modifier) {
            component.content.forEachIndexed { index, item ->
                BottomNavigationItem(
                    selected = index == pagerState.value?.currentPage,
                    icon = { item.icon.build() },
                    label = { item.label.build() },
                    onClick = { item.onClick.build() }
                )
            }
        }
    }
}

internal class UIHorizontalPagerComposable : UIElementComposable<UIHorizontalPager>() {

    @OptIn(ExperimentalFoundationApi::class)
    @Composable
    override fun SDUIContainerScope.build(modifier: Modifier, component: UIHorizontalPager) {

        val state = rememberPagerState(initialPage = 0, pageCount = { component.pages.size }).also {
            pagerState.value = it
        }

        HorizontalPager(
            modifier = modifier,
            state = state,
            pageContent = {
                component.pages[it].build()
            }
        )
    }
}