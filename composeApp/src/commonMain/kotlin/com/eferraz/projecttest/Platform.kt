package com.eferraz.projecttest

interface Platform {
    val name: String
}

expect fun getPlatform(): Platform