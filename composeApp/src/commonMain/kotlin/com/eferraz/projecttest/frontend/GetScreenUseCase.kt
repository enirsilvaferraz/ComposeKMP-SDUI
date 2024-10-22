package com.eferraz.projecttest.frontend

import com.eferraz.projecttest.backend.api.UIElement

internal class GetScreenUseCase(
    private val repository: ScreenRepository
) {
    suspend operator fun invoke(): UIElement = repository.get()
}