package com.eferraz.projecttest.backend.di

import android.content.Context
import androidx.room.Room
import androidx.room.RoomDatabase
import com.eferraz.projecttest.backend.datasources.local.room.AppDatabase
import org.koin.android.ext.koin.androidContext
import org.koin.core.module.Module
import org.koin.dsl.module

fun getUserDatabase(context: Context): RoomDatabase.Builder<AppDatabase> {
    val appContext = context.applicationContext
    val dbFile = appContext.getDatabasePath("my_room.db")
    return Room.databaseBuilder<AppDatabase>(
        context = appContext,
        name = dbFile.absolutePath
    )
}

actual val platformBackendModule: Module = module {
    single { getUserDatabase(androidContext()) }
}
