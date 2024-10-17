package com.eferraz.projecttest.network

import io.ktor.client.*
import io.ktor.client.call.*
import io.ktor.client.request.*
import kotlinx.serialization.Serializable

class DataSourceRemote(
    private val httpClient: HttpClient
) {

    suspend fun get() = httpClient.get(
        urlString = "https://pokeapi.co/api/v2/pokemon/"
    ).body<PaginationResult<PokemonRef>>()
}

@Serializable
data class PaginationResult <T>(
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