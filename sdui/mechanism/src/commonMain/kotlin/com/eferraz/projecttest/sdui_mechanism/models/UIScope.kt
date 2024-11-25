package com.eferraz.projecttest.sdui_mechanism.models

import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.lazy.LazyListScope
import com.eferraz.projecttest.sdui_mechanism.SDUIScreenScope

sealed class UIScope(open val name: String)
class UIAnyScope(val scope: SDUIScreenScope) : UIScope("ui-screen-scope")
class UIBottomBarScope(val index: Int, val scope: RowScope, val screen: UIAnyScope) : UIScope("ui-bottom-bar-scope")
class UILazyListScope(val scope: LazyListScope, val screen: UIAnyScope) : UIScope("ui-lazy-list-scope")
class UIRowScope(val scope: RowScope, val screen: UIAnyScope) : UIScope("ui-row-scope")