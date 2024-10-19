package com.eferraz.projecttest.backend.api

import com.eferraz.projecttest.network.DataSourceRemote
import com.eferraz.projecttest.network.PaginationResult
import com.eferraz.projecttest.network.PokemonRef
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import kotlinx.serialization.KSerializer
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json

public interface HomeApi {

    suspend fun get(): String
}

internal class HomeApiImpl(
    private val ds: DataSourceRemote,
) : HomeApi {

    override suspend fun get() = withContext(Dispatchers.Default) {
        Json.encodeToString(ds.get().toSDUI())
    }
}

fun PaginationResult<PokemonRef>.toSDUI() : SDUIComponent = SDUIScreen(body = this.results.map { SDUIText(text = it.name) })

@Serializable
sealed class SDUIComponent {

    @OptIn(kotlinx.serialization.ExperimentalSerializationApi::class)
    fun getSerialName() = getSerializer().descriptor.serialName
    abstract fun getSerializer() : KSerializer<out SDUIComponent>
}

@Serializable
@SerialName("sdui-screen")
data class SDUIScreen(val body: List<SDUIComponent>) : SDUIComponent() {
    override fun getSerializer() = serializer()
}

@Serializable
@SerialName("sdui-text")
data class SDUIText(val text: String) : SDUIComponent() {
    override fun getSerializer() = serializer()
}