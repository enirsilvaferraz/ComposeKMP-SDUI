package com.eferraz.projecttest

import android.app.Application
import com.eferraz.projecttest.frontend.initKoin
import org.koin.android.ext.koin.androidContext

class MainApplication : Application() {

    override fun onCreate() {
        super.onCreate()

        initKoin {
            androidContext(this@MainApplication)
        }
    }
}