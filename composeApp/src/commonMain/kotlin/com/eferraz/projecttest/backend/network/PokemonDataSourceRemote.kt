package com.eferraz.projecttest.backend.network

import io.ktor.client.*
import io.ktor.client.call.*
import io.ktor.client.request.*
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

class PokemonDataSourceRemote(
    private val httpClient: HttpClient
) {

    suspend fun pokemons() = httpClient.get(
        urlString = "https://pokeapi.co/api/v2/pokemon/"
    ).body<PaginationResult<PokemonRef>>()

    suspend fun pokemon(name:String) = httpClient.get(
        urlString = "https://pokeapi.co/api/v2/pokemon/$name"
    ).body<PokemonDetail>()
}

@Serializable
data class PaginationResult<T>(
    val count: Int,
    val next: String,
    val previous: String? = null,
    val results: List<T>
)

@Serializable
data class PokemonRef(
    val name: String,
    val url: String
)

@Serializable
data class PokemonDetail(
    val id: Int,
    val name: String,
    val sprites: Sprites
) {

    @Serializable
    data class Sprites(
        @SerialName("front_default")
        val frontDefault: String
    )
}