package com.eferraz.projecttest.backend.api

import com.eferraz.projecttest.network.DataSourceRemote
import com.eferraz.projecttest.network.PaginationResult
import com.eferraz.projecttest.network.PokemonRef
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json

public interface HomeApi {

    suspend fun get(): String
}

internal class HomeApiImpl(
    private val ds: DataSourceRemote,
    private val json: Json
) : HomeApi {

    override suspend fun get() = withContext(Dispatchers.Default) {
        json.encodeToString(ds.get().toSDUI())
    }
}

fun PaginationResult<PokemonRef>.toSDUI() : SDUIComponent = SDUIScreen(body = this.results.map { SDUIText(text = it.name) })

