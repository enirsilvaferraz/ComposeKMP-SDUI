package com.eferraz.projecttest.frontend

import com.eferraz.projecttest.backend.api.ApiOrchestror
import com.eferraz.projecttest.backend.api.UIElement
import kotlinx.serialization.json.Json

internal class ScreenRepository(
    private val api: ApiOrchestror,
    private val json: Json
) {
    suspend fun get(): UIElement = json.decodeFromString(api.get())
}