package com.eferraz.projecttest.sdui_components

import androidx.compose.foundation.ExperimentalFoundationApi
import com.eferraz.projecttest.sdui_mechanism.SDUIContainerScope
import com.eferraz.projecttest.sdui_mechanism.UIActionBehavior
import kotlinx.coroutines.launch


//internal class UINavigationBehavior : UIActionBehavior<UINavigation>() {
//    override fun SDUIContainerScope.build(action: UINavigation) {
//        navController.navigate(action.destination)
//    }
//}

internal class UIChangePageBehavior : UIActionBehavior<UIChangePage>() {

    @OptIn(ExperimentalFoundationApi::class)
    override fun SDUIContainerScope.build(action: UIChangePage) {
        coroutineScope.launch {
            pagerState.value?.animateScrollToPage(action.nextPage)
        }
    }
}