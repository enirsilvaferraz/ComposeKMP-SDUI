package com.eferraz.projecttest.backend.api

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Settings
import com.eferraz.projecttest.backend.network.PokemonDataSourceRemote
import com.eferraz.projecttest.backend.network.PaginationResult
import com.eferraz.projecttest.backend.network.PokemonDetail
import com.eferraz.projecttest.backend.network.PokemonRef
import com.eferraz.projecttest.sdui_components.*
import com.eferraz.projecttest.sdui_components.UIChangePage
import com.eferraz.projecttest.sdui_mechanism.UIElement
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json

internal interface HomeApi {

    suspend fun get(): String
}

internal class HomeApiImpl(
    private val ds: PokemonDataSourceRemote,
    private val json: Json,
) : HomeApi {

    override suspend fun get() = withContext(Dispatchers.Default) {
        json.encodeToString(ds.pokemons().results.map{ ds.pokemon(it.name) }.toSDUI())
    }
}

private fun List<PokemonDetail>.toSDUI(): UIElement = UIScaffold(
    topBar = UITopBar(title = UIText(text = "Pokedex")),
    content = UILazyColumn(
        body = this.map {
            UIRow(
                pages = listOf(
                    UIImage(it.sprites.frontDefault, it.name),
                    UIText(text = it.name)
                )
            )
        }
    )
)