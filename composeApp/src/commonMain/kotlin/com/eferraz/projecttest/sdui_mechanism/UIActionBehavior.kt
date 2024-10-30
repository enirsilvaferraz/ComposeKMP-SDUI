package com.eferraz.projecttest.sdui_mechanism

internal abstract class UIActionBehavior<Action : UIAction> {
    abstract fun SDUIContainerScope.build(action: Action)
}