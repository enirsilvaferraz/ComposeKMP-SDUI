package com.eferraz.projecttest.frontend.core

internal class GetScreenUseCase(
    private val repository: ScreenRepository
) {
    suspend operator fun invoke(): UIElement = repository.get()
}