package com.eferraz.projecttest.sdui_solution

import com.eferraz.projecttest.backend.ApiOrchestror
import com.eferraz.projecttest.sdui_mechanism.UIElement
import kotlinx.serialization.json.Json

internal class ScreenRepository(
    private val api: ApiOrchestror,
    private val json: Json
) {
    suspend fun get(): UIElement = json.decodeFromString(api.get())
}