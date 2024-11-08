package com.eferraz.projecttest.sdui_components

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.material.BottomAppBar
import androidx.compose.material.BottomNavigationItem
import androidx.compose.material.Icon
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Settings
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.util.fastForEach
import coil3.compose.AsyncImage
import com.eferraz.projecttest.sdui_mechanism.SDUIScreenScope
import com.eferraz.projecttest.sdui_mechanism.models.UIComponentImpl

internal class UIScaffoldComponentImpl : UIComponentImpl<UIScaffold>() {

    @Composable
    override fun SDUIScreenScope.build(modifier: Modifier, component: UIScaffold) {

        Scaffold(
            modifier = modifier,
            topBar = { component.topBar?.build() },
            content = { component.content.build() },
            bottomBar = { component.bottonBar?.build() }
        )
    }
}

internal class UILazyColumnComponentImpl : UIComponentImpl<UILazyColumn>() {

    @Composable
    override fun SDUIScreenScope.build(modifier: Modifier, component: UILazyColumn) {

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

internal class UITopBarComponentImpl : UIComponentImpl<UITopBar>() {

    @Composable
    override fun SDUIScreenScope.build(modifier: Modifier, component: UITopBar) {

        TopAppBar(
            modifier = modifier,
            title = { component.title.build() }
        )
    }
}

internal class UITextComponentImpl : UIComponentImpl<UIText>() {

    @Composable
    override fun SDUIScreenScope.build(modifier: Modifier, component: UIText) {

        Text(
            modifier = modifier,
            text = component.text
        )
    }
}

internal class UIIconComponentImpl : UIComponentImpl<UIIcon>() {

    @Composable
    override fun SDUIScreenScope.build(modifier: Modifier, component: UIIcon) {

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

internal class UIBottomBarComponentImpl : UIComponentImpl<UIBottomBar>() {

    @OptIn(ExperimentalFoundationApi::class)
    @Composable
    override fun SDUIScreenScope.build(modifier: Modifier, component: UIBottomBar) {

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

internal class UIHorizontalPagerComponentImpl : UIComponentImpl<UIHorizontalPager>() {

    @OptIn(ExperimentalFoundationApi::class)
    @Composable
    override fun SDUIScreenScope.build(modifier: Modifier, component: UIHorizontalPager) {

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

internal class UIRowComponentImpl : UIComponentImpl<UIRow>() {

    @Composable
    override fun SDUIScreenScope.build(modifier: Modifier, component: UIRow) {
        Row(verticalAlignment = component.verticalAlignment.alignment) {
            component.content.map { it.build() }
        }
    }
}

internal class UIImageComponentImpl : UIComponentImpl<UIImage>() {

    @Composable
    override fun SDUIScreenScope.build(modifier: Modifier, component: UIImage) {
        AsyncImage(
            modifier = modifier,
            model = component.url,
            contentDescription = component.contentDescription
        )
    }
}