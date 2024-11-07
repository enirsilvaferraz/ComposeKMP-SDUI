package com.eferraz.projecttest.sdui_solution

import com.eferraz.projecttest.backend.api.ApiOrchestrator
import com.eferraz.projecttest.sdui_mechanism.models.UIComponent
import kotlinx.serialization.json.Json

internal class ScreenRepository(
    private val api: ApiOrchestrator,
    private val json: Json
) {
    suspend fun get(): UIComponent = json.decodeFromString(api.get())
}