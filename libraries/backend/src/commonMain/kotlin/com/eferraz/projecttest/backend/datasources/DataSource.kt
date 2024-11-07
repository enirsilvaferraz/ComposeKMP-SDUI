package com.eferraz.projecttest.backend.datasources

interface ReadableDataSource <T>{

    suspend fun get(id: Long) : T?
}

interface WritableDataSource <T> : ReadableDataSource<T>{

    suspend fun insert(model: T)
}