package com.eferraz.projecttest.frontend.core

import com.eferraz.projecttest.frontend.NavRoot
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
abstract class UIAction

@Serializable
@SerialName("ui-navigation")
internal class UINavigation(val destination: NavRoot) : UIAction()

@Serializable
@SerialName("ui-change-page")
internal class UIChangePage(val nextPage: Int) : UIAction()