package com.eferraz.projecttest.backend.api

import androidx.compose.ui.text.capitalize
import androidx.compose.ui.text.intl.Locale
import com.eferraz.projecttest.backend.Pokemon
import com.eferraz.projecttest.backend.repository.PokemonRepository
import com.eferraz.projecttest.sdui_components.UIImage
import com.eferraz.projecttest.sdui_components.UILazyColumn
import com.eferraz.projecttest.sdui_components.UIRow
import com.eferraz.projecttest.sdui_components.UIScaffold
import com.eferraz.projecttest.sdui_components.UIText
import com.eferraz.projecttest.sdui_components.UITopBar
import com.eferraz.projecttest.sdui_mechanism.UIElement
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json

 interface HomeApi {

    suspend fun get(): String
}

internal class HomeApiImpl(
    private val ds: PokemonRepository,
    private val json: Json,
) : HomeApi {

    override suspend fun get() = withContext(Dispatchers.Default) {
        json.encodeToString(ds.get(0, 50).toSDUI())
    }
}

private fun List<Pokemon>.toSDUI(): UIElement = UIScaffold(
    topBar = UITopBar(title = UIText(text = "Pokedex")),
    content = UILazyColumn(body = this.map { it.itemList() })
)

private fun Pokemon.itemList() = UIRow(
    verticalAlignment = UIRow.VerticalAlignment.CENTER_VERTICALLY,
    content = arrayListOf<UIElement>().apply {
        if (sprites.frontDefault != null)
            add(UIImage(url = sprites.frontDefault, contentDescription = name))
        add(UIText(text = name.capitalize(Locale.current)))
    }
)