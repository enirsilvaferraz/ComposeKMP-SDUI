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
import com.eferraz.projecttest.sdui_domain.UIBottomBar
import com.eferraz.projecttest.sdui_domain.UIBottomNavigationItem
import com.eferraz.projecttest.sdui_domain.UIHorizontalPager
import com.eferraz.projecttest.sdui_domain.UIIcon
import com.eferraz.projecttest.sdui_domain.UIImage
import com.eferraz.projecttest.sdui_domain.UILazyColumn
import com.eferraz.projecttest.sdui_domain.UIRow
import com.eferraz.projecttest.sdui_domain.UIScaffold
import com.eferraz.projecttest.sdui_domain.UIText
import com.eferraz.projecttest.sdui_domain.UITopBar
import com.eferraz.projecttest.sdui_mechanism.SDUIScope.build
import com.eferraz.projecttest.sdui_mechanism.models.UIBottomBarScope
import com.eferraz.projecttest.sdui_mechanism.models.UIComponentImpl
import com.eferraz.projecttest.sdui_mechanism.models.UIAnyScope

internal class UIScaffoldComponentImpl : UIComponentImpl<UIScaffold, UIAnyScope>() {

    @Composable
    override fun UIAnyScope.build(modifier: Modifier, component: UIScaffold) {

        Scaffold(
            modifier = modifier,
            topBar = { component.topBar?.build(this) },
            content = { component.content.build(this) },
            bottomBar = { component.bottomBar?.build(this) }
        )
    }
}

internal class UILazyColumnComponentImpl : UIComponentImpl<UILazyColumn, UIAnyScope>() {

    @Composable
    override fun UIAnyScope.build(modifier: Modifier, component: UILazyColumn) {

        LazyColumn(
            modifier = modifier,
            content = {
                component.body.fastForEach {
                    item {
                        it.build(this@build)
                    }
                }
            }
        )
    }
}

internal class UITopBarComponentImpl : UIComponentImpl<UITopBar, UIAnyScope>() {

    @Composable
    override fun UIAnyScope.build(modifier: Modifier, component: UITopBar) {

        TopAppBar(
            modifier = modifier,
            title = { component.title.build(this) }
        )
    }
}

internal class UITextComponentImpl : UIComponentImpl<UIText, UIAnyScope>() {

    @Composable
    override fun UIAnyScope.build(modifier: Modifier, component: UIText) {

        Text(
            modifier = modifier,
            text = component.text
        )
    }
}

internal class UIIconComponentImpl : UIComponentImpl<UIIcon, UIAnyScope>() {

    @Composable
    override fun UIAnyScope.build(modifier: Modifier, component: UIIcon) {

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

internal class UIBottomBarComponentImpl : UIComponentImpl<UIBottomBar, UIAnyScope>() {

    @OptIn(ExperimentalFoundationApi::class)
    @Composable
    override fun UIAnyScope.build(modifier: Modifier, component: UIBottomBar) {

        BottomAppBar(modifier = modifier) {
//            component.content.forEachIndexed { index, item ->
//                BottomNavigationItem(
//                    selected = index == scope.pagerState.value?.currentPage,
//                    icon = { item.icon.build(this@build) },
//                    label = { item.label.build(this@build) },
//                    onClick = { item.onClick.build(this@build) }
//                )
//            }
            component.content.mapIndexed { index, item ->
                item.build(UIBottomBarScope(index, this@BottomAppBar, this@build))
            }
        }
    }
}

internal class UIBottomNavigationItemImpl : UIComponentImpl<UIBottomNavigationItem, UIBottomBarScope>() {

    @OptIn(ExperimentalFoundationApi::class)
    @Composable
    override fun UIBottomBarScope.build(modifier: Modifier, component: UIBottomNavigationItem) {
        with(scope) {
            BottomNavigationItem(
                selected = index == screen.scope.pagerState.value?.currentPage,
                icon = { component.icon.build(this@build) },
                label = { component.label.build(this@build) },
                onClick = { component.onClick.build(this@build) }
            )
        }
    }
}

internal class UIHorizontalPagerComponentImpl : UIComponentImpl<UIHorizontalPager, UIAnyScope>() {

    @OptIn(ExperimentalFoundationApi::class)
    @Composable
    override fun UIAnyScope.build(modifier: Modifier, component: UIHorizontalPager) {

        val state = rememberPagerState(initialPage = 0, pageCount = { component.pages.size }).also {
            scope.pagerState.value = it
        }

        HorizontalPager(
            modifier = modifier,
            state = state,
            pageContent = {
                component.pages[it].build(this@build)
            }
        )
    }
}

internal class UIRowComponentImpl : UIComponentImpl<UIRow, UIAnyScope>() {

    @Composable
    override fun UIAnyScope.build(modifier: Modifier, component: UIRow) {
        Row(
            modifier = modifier,
            verticalAlignment = component.verticalAlignment.alignment
        ) {
            component.content.map { it.build(this@build) }
        }
    }
}

internal class UIImageComponentImpl : UIComponentImpl<UIImage, UIAnyScope>() {

    @Composable
    override fun UIAnyScope.build(modifier: Modifier, component: UIImage) {
        AsyncImage(
            modifier = modifier,
            model = component.url,
            contentDescription = component.contentDescription
        )
    }
}