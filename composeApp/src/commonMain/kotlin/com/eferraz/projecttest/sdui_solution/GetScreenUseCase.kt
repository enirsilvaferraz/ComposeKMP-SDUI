package com.eferraz.projecttest.sdui_solution

import com.eferraz.projecttest.sdui_mechanism.models.UIComponent

internal class GetScreenUseCase(
    private val repository: ScreenRepository
) {
    suspend operator fun invoke(): UIComponent = repository.get()
}