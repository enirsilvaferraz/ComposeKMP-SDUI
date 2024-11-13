package com.eferraz.projecttest.sdui_components

import androidx.compose.foundation.ExperimentalFoundationApi
import com.eferraz.projecttest.sdui_domain.UIChangePage
import com.eferraz.projecttest.sdui_domain.UINavigate
import com.eferraz.projecttest.sdui_mechanism.models.UIActionImpl
import com.eferraz.projecttest.sdui_mechanism.models.UIAnyScope
import kotlinx.coroutines.launch


internal class UINavigateImpl : UIActionImpl<UINavigate, UIAnyScope>() {
    override fun UIAnyScope.build(action: UINavigate) {
        scope.navController.navigate(action.deeplink)
    }
}

internal class UIChangePageImpl : UIActionImpl<UIChangePage, UIAnyScope>() {

    @OptIn(ExperimentalFoundationApi::class)
    override fun UIAnyScope.build(action: UIChangePage) {
        scope.coroutineScope.launch {
            scope.pagerState.value?.animateScrollToPage(action.nextPage)
        }
    }
}