package com.eferraz.projecttest.backend.repository

import com.eferraz.projecttest.backend.Pokemon
import com.eferraz.projecttest.backend.datasources.local.PokemonDataSourceDao
import com.eferraz.projecttest.backend.datasources.remote.PokemonDataSourceRemote

internal class PokemonRepository(
    private val remote: PokemonDataSourceRemote,
    private val local: PokemonDataSourceDao
) {

    suspend fun get(offset: Int, limit: Int): List<Pokemon> {

        val result = arrayListOf<Pokemon>()

        for (id in (offset + 1)..offset + limit) {
            result.add(
                local.get(id.toLong())?.toModel() ?: remote.pokemon(id.toLong()).also { local.insert(it.toEntity()) }
            )
        }

        return result
    }
}