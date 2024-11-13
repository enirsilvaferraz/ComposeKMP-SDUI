package com.eferraz.projecttest.sdui_domain

import com.eferraz.projecttest.sdui_mechanism.models.UIAction
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
@SerialName("ui-action-navigate")
class UINavigate(val deeplink: String) : UIAction()

@Serializable
@SerialName("ui-action-change-page")
class UIChangePage(val nextPage: Int) : UIAction()