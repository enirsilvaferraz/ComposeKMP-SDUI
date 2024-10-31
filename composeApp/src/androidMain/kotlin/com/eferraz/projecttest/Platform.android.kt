package com.eferraz.projecttest

import android.content.Context
import android.os.Build
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.sqlite.driver.bundled.BundledSQLiteDriver
import com.eferraz.projecttest.backend.repository.AppDatabase
import kotlinx.coroutines.Dispatchers
import org.koin.android.ext.koin.androidContext
import org.koin.core.module.Module
import org.koin.dsl.module

fun getUserDatabase(context: Context): RoomDatabase.Builder<AppDatabase> {
//    val dbFile = context.getDatabasePath("app.db")
//    return Room.databaseBuilder<AppDatabase>(
//        context = context.applicationContext,
//        name = dbFile.absolutePath
//    )
//        .fallbackToDestructiveMigrationOnDowngrade(true)
//        .setDriver(BundledSQLiteDriver()) // Very important
//        .setQueryCoroutineContext(Dispatchers.IO)
    val appContext = context.applicationContext
    val dbFile = appContext.getDatabasePath("my_room.db")
    return Room.databaseBuilder<AppDatabase>(
      context = appContext,
      name = dbFile.absolutePath
    )
}
class AndroidPlatform : Platform {
    override val name: String = "Android ${Build.VERSION.SDK_INT}"
}

actual fun getPlatform(): Platform = AndroidPlatform()


actual val platformModule: Module = module {
    single { getUserDatabase(androidContext()) }
}