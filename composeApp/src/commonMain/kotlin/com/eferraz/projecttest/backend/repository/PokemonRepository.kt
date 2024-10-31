package com.eferraz.projecttest.backend.repository

import androidx.room.*
import androidx.sqlite.driver.bundled.BundledSQLiteDriver
import com.eferraz.projecttest.backend.Pokemon
import com.eferraz.projecttest.backend.network.PokemonDataSourceRemote
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.IO
import kotlinx.coroutines.flow.Flow
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json

internal class PokemonRepository(
    private val remote: PokemonDataSourceRemote,
    private val database: AppDatabase
) {

    suspend fun save() {

        val results = remote.pokemons().results

        results.forEach {
            val saved = database.getDao().get(it.name)
            if (saved == null) {
                val pokemon = remote.pokemon(it.name)
                database.getDao().insert(pokemon.toEntity())
            }
        }
    }

    suspend fun get() : List<Pokemon> {
        save()
        return database.getDao().getAll().map { Json.decodeFromString(it.json) }
    }
}

fun Pokemon.toEntity() = PokemonEntity(this.name, Json.encodeToString(this))


fun getRoomDatabase(
    builder: RoomDatabase.Builder<AppDatabase>
): AppDatabase {
  return builder
//      .addMigrations(MIGRATIONS)
//      .fallbackToDestructiveMigrationOnDowngrade()
      .setDriver(BundledSQLiteDriver())
      .setQueryCoroutineContext(Dispatchers.IO)
      .build()
}

@Database(entities = [PokemonEntity::class], version = 1)
@ConstructedBy(AppDatabaseConstructor::class)
abstract class AppDatabase : RoomDatabase() {
  abstract fun getDao(): PokemonDao
}

// The Room compiler generates the `actual` implementations.
@Suppress("NO_ACTUAL_FOR_EXPECT")
expect object AppDatabaseConstructor : RoomDatabaseConstructor<AppDatabase> {
    override fun initialize(): AppDatabase
}

@Dao
interface PokemonDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(vararg pokemon: PokemonEntity)


    @Query("SELECT * FROM PokemonEntity WHERE name = :name")
    suspend fun get(name: String): PokemonEntity?

    @Query("SELECT * FROM PokemonEntity")
    suspend fun getAll(): List<PokemonEntity>
}

@Entity
data class PokemonEntity(
    @PrimaryKey val name: String,
    val json: String
)