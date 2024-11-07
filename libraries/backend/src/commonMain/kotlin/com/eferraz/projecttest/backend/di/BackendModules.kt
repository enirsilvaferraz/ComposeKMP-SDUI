package com.eferraz.projecttest.backend.di

import com.eferraz.projecttest.backend.api.ApiOrchestrator
import com.eferraz.projecttest.backend.api.ApiOrchestratorImpl
import com.eferraz.projecttest.backend.api.HomeApi
import com.eferraz.projecttest.backend.api.HomeApiImpl
import com.eferraz.projecttest.backend.datasources.ReadableDataSource
import com.eferraz.projecttest.backend.datasources.WritableDataSource
import com.eferraz.projecttest.backend.datasources.local.PokemonDao
import com.eferraz.projecttest.backend.datasources.local.AppDatabase
import com.eferraz.projecttest.backend.datasources.local.PokemonDataSourceLocal
import com.eferraz.projecttest.backend.datasources.local.RoomDatabaseFactory
import com.eferraz.projecttest.backend.datasources.remote.PokemonDataSourceRemote
import com.eferraz.projecttest.backend.datasources.remote.NetworkFactory
import com.eferraz.projecttest.backend.repository.PokemonRepository
import org.koin.core.module.Module
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.bind
import org.koin.dsl.module


val backendModule = module {

    /*
     * Internal Configurations
     */
    includes(platformBackendModule)

    single { NetworkFactory().build() }

    single { RoomDatabaseFactory(get()).build() }
    single { get<AppDatabase>().getDao() } bind PokemonDao::class


    /*
     * Internal Data Sources
     */

    singleOf(::PokemonDataSourceRemote) bind ReadableDataSource::class
    singleOf(::PokemonDataSourceLocal) bind WritableDataSource::class

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

    singleOf(::ApiOrchestratorImpl) bind ApiOrchestrator::class
}

expect val platformBackendModule: Module