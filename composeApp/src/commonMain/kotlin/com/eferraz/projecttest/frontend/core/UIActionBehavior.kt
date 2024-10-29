package com.eferraz.projecttest.frontend.core

import androidx.compose.foundation.ExperimentalFoundationApi
import com.eferraz.projecttest.frontend.SDUIContainerScope
import kotlinx.coroutines.launch

internal abstract class UIActionBehavior<Action : UIAction> {
    abstract fun SDUIContainerScope.build(action: Action)
}


internal class UINavigationBehavior : UIActionBehavior<UINavigation>() {
    override fun SDUIContainerScope.build(action: UINavigation) {
        navController.navigate(action.destination)
    }
}

internal class UIChangePageBehavior : UIActionBehavior<UIChangePage>() {

    @OptIn(ExperimentalFoundationApi::class)
    override fun SDUIContainerScope.build(action: UIChangePage) {
        coroutineScope.launch {
            pagerState.value?.animateScrollToPage(action.nextPage)
        }
    }
}