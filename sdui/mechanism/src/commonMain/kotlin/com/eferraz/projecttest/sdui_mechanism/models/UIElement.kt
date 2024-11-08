package com.eferraz.projecttest.sdui_mechanism.models

import kotlinx.serialization.Serializable

/**
 * Classe base para todos os elementos do SDUI.
 */
@Serializable
abstract class UIElement

/**
 * Classe base para todos os elementos do SDUI que podem ser renderizados.
 * Ex.: Buttons, Text, Images, etc.
 */
@Serializable
abstract class UIComponent : UIElement() {
    abstract val modifier: List<UIModifier>
}

/**
 * Classe base para todos os elementos do SDUI que podem ser interativos.
 * Devem ser chamados a partir de eventos.
 */
@Serializable
abstract class UIAction : UIElement()