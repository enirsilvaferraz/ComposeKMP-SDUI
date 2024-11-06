package com.eferraz.projecttest.backend.di

import com.eferraz.projecttest.backend.ApiOrchestror
import com.eferraz.projecttest.backend.api.HomeApi
import com.eferraz.projecttest.backend.api.HomeApiImpl
import com.eferraz.projecttest.backend.datasources.local.PokemonDataSourceDao
import com.eferraz.projecttest.backend.datasources.local.room.AppDatabase
import com.eferraz.projecttest.backend.datasources.local.room.RoomDatabaseFactory
import com.eferraz.projecttest.backend.datasources.remote.PokemonDataSourceRemote
import com.eferraz.projecttest.backend.datasources.remote.ktor.NetworkFactory
import com.eferraz.projecttest.backend.repository.PokemonRepository
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.bind
import org.koin.dsl.module


val backendModule = module {

    /*
     * Internal Configurations
     */

    single { NetworkFactory().build() }
    single { RoomDatabaseFactory(get()).build() }

    /*
     * Internal Data Sources
     */

    single { get<AppDatabase>().getDao() } bind PokemonDataSourceDao::class
    singleOf(::PokemonDataSourceRemote) bind PokemonDataSourceRemote::class

    /*
     * Internal Repositories
     */

    singleOf(::PokemonRepository)

    /*
     * Internal APIs
     */

    singleOf(::HomeApiImpl) bind HomeApi::class

    /*
     * Public APIs
     */

    singleOf(::ApiOrchestror)
}