package com.eferraz.projecttest.backend.di

import com.eferraz.projecttest.backend.api.HomeApi
import com.eferraz.projecttest.backend.api.HomeApiImpl
import com.eferraz.projecttest.backend.ApiOrchestror
import com.eferraz.projecttest.backend.network.DataSourceRemote
import com.eferraz.projecttest.backend.network.createHttpClient
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.bind

import org.koin.dsl.module


val backendModule = module {

    single { createHttpClient() }

    singleOf(::ApiOrchestror)

    singleOf(::HomeApiImpl) bind HomeApi::class
    
    singleOf(::DataSourceRemote)
}