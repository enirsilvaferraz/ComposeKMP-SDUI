package com.eferraz.projecttest.frontend.core

import com.eferraz.projecttest.backend.ApiOrchestror
import kotlinx.serialization.json.Json

internal class ScreenRepository(
    private val api: ApiOrchestror,
    private val json: Json
) {
    suspend fun get(): UIElement = json.decodeFromString(api.get())
}