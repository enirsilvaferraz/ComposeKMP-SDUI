package com.eferraz.projecttest.backend.api

r

internal class ApiOrchestror(
    private val callBuilder: CallBuilder,
    private val homeApi: HomeApi
) {

    suspend fun get(): String = when (callBuilder.url) {
        "/home" -> homeApi.get()
        else -> throw IllegalArgumentException("URL not found!")
    }
}