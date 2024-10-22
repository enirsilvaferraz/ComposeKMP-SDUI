package com.eferraz.projecttest.backend.api

import com.eferraz.projecttest.backend.network.DataSourceRemote
import com.eferraz.projecttest.backend.network.PaginationResult
import com.eferraz.projecttest.backend.network.PokemonRef
import com.eferraz.projecttest.frontend.core.UIElement
import com.eferraz.projecttest.frontend.core.UIScafold
import com.eferraz.projecttest.frontend.core.UIText
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json

internal interface HomeApi {

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

private fun PaginationResult<PokemonRef>.toSDUI() : UIElement = UIScafold(body = this.results.map { UIText(text = it.name) })

