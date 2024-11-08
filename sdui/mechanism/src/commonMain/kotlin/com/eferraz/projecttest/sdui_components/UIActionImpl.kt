package com.eferraz.projecttest.sdui_components

import androidx.compose.foundation.ExperimentalFoundationApi
import com.eferraz.projecttest.sdui_mechanism.SDUIScreenScope
import com.eferraz.projecttest.sdui_mechanism.models.UIActionImpl
import kotlinx.coroutines.launch


//internal class UINavigationBehavior : UIActionBehavior<UINavigation>() {
//    override fun SDUIContainerScope.build(action: UINavigation) {
//        navController.navigate(action.destination)
//    }
//}

internal class UIChangePageImpl : UIActionImpl<UIChangePage>() {

    @OptIn(ExperimentalFoundationApi::class)
    override fun SDUIScreenScope.build(action: UIChangePage) {
        coroutineScope.launch {
            pagerState.value?.animateScrollToPage(action.nextPage)
        }
    }
}