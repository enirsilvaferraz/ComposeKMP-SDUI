package com.eferraz.projecttest

import androidx.compose.ui.window.ComposeUIViewController
import com.eferraz.projecttest.frontend.App
import com.eferraz.projecttest.frontend.initKoin

fun MainViewController() = ComposeUIViewController(
    configure ={
        initKoin()
    }
) {
    App()
}