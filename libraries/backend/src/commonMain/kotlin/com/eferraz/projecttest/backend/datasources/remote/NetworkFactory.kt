package com.eferraz.projecttest.backend.datasources.remote

import io.ktor.client.*
import io.ktor.client.plugins.cache.*
import io.ktor.client.plugins.contentnegotiation.*
import io.ktor.client.plugins.logging.*
import io.ktor.serialization.kotlinx.json.*
import kotlinx.serialization.json.Json
import org.lighthousegames.logging.logging

class NetworkFactory {

    fun build() = HttpClient {

        install(Logging) {
            level = LogLevel.ALL
            logger = object : Logger {
                override fun log(message: String) {
                    logging("KTOR HTTP Call").d { message }
                }
            }
        }

        install(ContentNegotiation) {
            json(
                json = Json {
                    ignoreUnknownKeys = true
                    coerceInputValues = true
                }
            )
        }

        install(HttpCache)
    }
}