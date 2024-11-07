package com.eferraz.projecttest.backend.api

/**
 * Simulador de orquestrador de urls.
 */
interface ApiOrchestrator {
    suspend fun get(): String
}

internal class ApiOrchestratorImpl(
    private val url: String,
    private val homeApi: HomeApi
): ApiOrchestrator {

    override suspend fun get(): String = when (url) {
        "/home" -> homeApi.get()
        else -> throw IllegalArgumentException("URL not found!")
    }
}