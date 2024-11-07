package com.eferraz.projecttest.backend.repository

import com.eferraz.projecttest.backend.datasources.local.PokemonDataSourceLocal
import com.eferraz.projecttest.backend.datasources.remote.PokemonDataSourceRemote
import com.eferraz.projecttest.backend.models.Pokemon

internal class PokemonRepository(
    private val remote: PokemonDataSourceRemote,
    private val local: PokemonDataSourceLocal
) {

    suspend fun get(offset: Int, limit: Int): List<Pokemon> {

        val result = arrayListOf<Pokemon>()

        for (id in (offset + 1).toLong()..offset + limit) {
            result.add(
                local.get(id) ?: remote.get(id).also { local.insert(it) }
            )
        }

        return result
    }
}