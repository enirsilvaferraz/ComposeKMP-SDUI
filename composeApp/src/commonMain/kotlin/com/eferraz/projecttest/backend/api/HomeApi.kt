package com.eferraz.projecttest.backend.api

import com.eferraz.projecttest.backend.Pokemon
import com.eferraz.projecttest.backend.network.PokemonDataSourceRemote
import com.eferraz.projecttest.backend.network.PokemonDetail
import com.eferraz.projecttest.backend.network.PokemonType
import com.eferraz.projecttest.backend.repository.PokemonRepository
import com.eferraz.projecttest.sdui_components.*
import com.eferraz.projecttest.sdui_mechanism.UIElement
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json
import kotlin.math.min

internal interface HomeApi {

    suspend fun get(): String
}

internal class HomeApiImpl(
    private val ds: PokemonRepository,
    private val json: Json,
) : HomeApi {

    override suspend fun get() = withContext(Dispatchers.Default) {

        val map = ds.get()

        json.encodeToString(map.toSDUI())
    }
}

private fun List<Pokemon>.toSDUI(): UIElement = UIScaffold(
    topBar = UITopBar(title = UIText(text = "Pokedex")),
    content = UILazyColumn(
        body = this.map {
            UIRow(pages = arrayListOf<UIElement>().apply {
                if (it.sprites.frontDefault != null)
                    add(UIImage(it.sprites.frontDefault!!, it.name))
                add(UIText(text = it.name))
            })
        }
    )
)