package com.eferraz.projecttest.backend.datasources.remote

import com.eferraz.projecttest.backend.Pokemon
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.get
import kotlinx.serialization.Serializable

internal class PokemonDataSourceRemote(
    private val httpClient: HttpClient
) {

    suspend fun pokemons(offset: Int, limit: Int) = httpClient.get(
        urlString = "https://pokeapi.co/api/v2/pokemon/?limit=$limit&offset=$offset"
    ).body<PaginationResult<ResourceRef>>()

    suspend fun pokemon(name: String) = httpClient.get(
        urlString = "https://pokeapi.co/api/v2/pokemon/$name"
    ).body<Pokemon>()

    suspend fun pokemon(id: Long) = httpClient.get(
        urlString = "https://pokeapi.co/api/v2/pokemon/$id"
    ).body<Pokemon>()
}

@Serializable
data class PaginationResult<T>(
    val count: Int,
    val next: String,
    val previous: String? = null,
    val results: List<T>
)

@Serializable
data class ResourceRef(
    val name: String,
    val url: String
)