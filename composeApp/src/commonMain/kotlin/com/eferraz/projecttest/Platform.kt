package com.eferraz.projecttest

import io.ktor.client.engine.*
import org.koin.core.module.Module

interface Platform {
    val name: String
}

expect fun getPlatform(): Platform

expect val platformModule: Module