package com.eferraz.projecttest

import org.koin.core.module.Module
import org.koin.dsl.module
import platform.UIKit.UIDevice
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.sqlite.driver.bundled.BundledSQLiteDriver
import com.eferraz.projecttest.backend.repository.AppDatabase
import kotlinx.cinterop.ExperimentalForeignApi
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.IO
import platform.Foundation.NSDocumentDirectory
import platform.Foundation.NSFileManager
import platform.Foundation.NSHomeDirectory // This shall too in Windows.
import platform.Foundation.NSUserDomainMask

fun getUserDatabase(): RoomDatabase.Builder<AppDatabase> {
    val dbFilePath = documentDirectory() + "/my_room.db"
    return Room.databaseBuilder<AppDatabase>(
        name = dbFilePath,
    )
}

@OptIn(ExperimentalForeignApi::class)
private fun documentDirectory(): String {
    val documentDirectory = NSFileManager.defaultManager.URLForDirectory(
      directory = NSDocumentDirectory,
      inDomain = NSUserDomainMask,
      appropriateForURL = null,
      create = false,
      error = null,
    )
    return requireNotNull(documentDirectory?.path)
  }

class IOSPlatform : Platform {
    override val name: String = UIDevice.currentDevice.systemName() + " " + UIDevice.currentDevice.systemVersion
}

actual fun getPlatform(): Platform = IOSPlatform()

actual val platformModule: Module = module {
    single { getUserDatabase() }
}