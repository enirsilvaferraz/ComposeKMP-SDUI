package com.eferraz.projecttest

import io.ktor.client.engine.*

interface Platform {
    val name: String
}

expect fun getPlatform(): Platform