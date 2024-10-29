package com.eferraz.projecttest.backend.api

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Settings
import com.eferraz.projecttest.backend.network.DataSourceRemote
import com.eferraz.projecttest.backend.network.PaginationResult
import com.eferraz.projecttest.backend.network.PokemonRef
import com.eferraz.projecttest.frontend.NavRoot
import com.eferraz.projecttest.frontend.core.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json

internal interface HomeApi {

    suspend fun get(): String
}

internal class HomeApiImpl(
    private val ds: DataSourceRemote,
    private val json: Json,
) : HomeApi {

    override suspend fun get() = withContext(Dispatchers.Default) {
        json.encodeToString(ds.get().toSDUI())
    }
}

private fun PaginationResult<PokemonRef>.toSDUI(): UIElement = UIScaffold(
    topBar = UITopBar(title = UIText(text = "Home")),
    content = UIHorizontalPager(
        pages = listOf(
            UILazyColumn(body = this.results.map { UIText(text = it.name) }),
            UILazyColumn(body = this.results.map { UIText(text = it.name) })
        )
    ),
    bottonBar = UIBottomBar(
        content = listOf(
            UIBottomBar.Item(icon = UIIcon(Icons.Default.Home.name), label = UIText("Home"), onClick = UIChangePage (0)),
            UIBottomBar.Item(icon = UIIcon(Icons.Default.Settings.name), label = UIText("Settings"), onClick = UIChangePage (1))
        )
    )
)