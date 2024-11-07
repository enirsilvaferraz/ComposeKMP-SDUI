package com.eferraz.projecttest.backend.datasources.local

import com.eferraz.projecttest.backend.datasources.WritableDataSource
import com.eferraz.projecttest.backend.models.Pokemon
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json

internal class PokemonDataSourceLocal(
    private val pokemonDao: PokemonDao
) : WritableDataSource<Pokemon> {

    override suspend fun get(id: Long): Pokemon? = pokemonDao.get(id)?.toModel()

    override suspend fun insert(model: Pokemon) = pokemonDao.insert(model.toEntity())
}

private fun PokemonEntity.toModel(): Pokemon = Json.decodeFromString(this.json)
private fun Pokemon.toEntity() = PokemonEntity(this.id, this.name, Json.encodeToString(this))