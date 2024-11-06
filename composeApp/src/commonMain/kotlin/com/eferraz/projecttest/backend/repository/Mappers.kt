package com.eferraz.projecttest.backend.repository

import com.eferraz.projecttest.backend.Pokemon
import com.eferraz.projecttest.backend.datasources.local.PokemonEntity
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json

internal fun Pokemon.toEntity() = PokemonEntity(this.id, this.name, Json.encodeToString(this))
internal fun PokemonEntity.toModel(): Pokemon = Json.decodeFromString(this.json)