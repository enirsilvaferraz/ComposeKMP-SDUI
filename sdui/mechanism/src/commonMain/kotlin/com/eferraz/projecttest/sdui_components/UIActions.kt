package com.eferraz.projecttest.sdui_components

//import com.eferraz.projecttest.frontend.NavRoot
import com.eferraz.projecttest.sdui_mechanism.models.UIAction
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

//@Serializable
//@SerialName("ui-navigation")
//internal class UINavigation(val destination: NavRoot) : UIAction()

@Serializable
@SerialName("ui-change-page")
internal class UIChangePage(val nextPage: Int) : UIAction()