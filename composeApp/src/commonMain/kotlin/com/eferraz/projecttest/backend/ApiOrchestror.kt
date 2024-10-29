package com.eferraz.projecttest.backend

import com.eferraz.projecttest.backend.api.HomeApi

internal class ApiOrchestror(
    private val url: String,
    private val homeApi: HomeApi
) {

    suspend fun get(): String = when (url) {
        "/home" -> homeApi.get()
        "/settings" -> homeApi.get()
        else -> throw IllegalArgumentException("URL not found!")
    }
}