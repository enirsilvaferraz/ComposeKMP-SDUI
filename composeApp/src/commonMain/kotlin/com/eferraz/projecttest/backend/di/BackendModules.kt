package com.eferraz.projecttest.backend.di

import com.eferraz.projecttest.backend.api.HomeApi
import com.eferraz.projecttest.backend.api.HomeApiImpl
import com.eferraz.projecttest.backend.ApiOrchestror
import com.eferraz.projecttest.backend.network.PokemonDataSourceRemote
import com.eferraz.projecttest.backend.network.createHttpClient
import com.eferraz.projecttest.backend.repository.PokemonRepository
import com.eferraz.projecttest.backend.repository.getRoomDatabase
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.bind

import org.koin.dsl.module


val backendModule = module {

    single { createHttpClient() }

    singleOf(::ApiOrchestror)

    singleOf(::HomeApiImpl) bind HomeApi::class
    
    singleOf(::PokemonDataSourceRemote)

    single {getRoomDatabase(get())}

    singleOf(::PokemonRepository)
}