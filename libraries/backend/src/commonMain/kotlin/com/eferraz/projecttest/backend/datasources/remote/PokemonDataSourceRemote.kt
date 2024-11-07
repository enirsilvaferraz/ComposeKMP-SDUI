package com.eferraz.projecttest.backend.datasources.remote

import com.eferraz.projecttest.backend.datasources.ReadableDataSource
import com.eferraz.projecttest.backend.models.Pokemon
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.get

internal class PokemonDataSourceRemote(
    private val httpClient: HttpClient
) : ReadableDataSource<Pokemon> {

    override suspend fun get(id: Long) = httpClient.get(
        urlString = "https://pokeapi.co/api/v2/pokemon/$id"
    ).body<Pokemon>()
}