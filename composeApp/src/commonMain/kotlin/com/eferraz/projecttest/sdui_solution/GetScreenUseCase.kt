package com.eferraz.projecttest.sdui_solution

import com.eferraz.projecttest.sdui_mechanism.UIElement

internal class GetScreenUseCase(
    private val repository: ScreenRepository
) {
    suspend operator fun invoke(): UIElement = repository.get()
}