package com.eferraz.projecttest.backend.datasources.local

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface PokemonDataSourceDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(vararg pokemon: PokemonEntity)

    @Query("SELECT * FROM PokemonEntity WHERE name = :name")
    suspend fun get(name: String): PokemonEntity?

    @Query("SELECT * FROM PokemonEntity WHERE id = :id")
    suspend fun get(id: Long): PokemonEntity?

    @Query("SELECT * FROM PokemonEntity ORDER BY id LIMIT :limit OFFSET :offset")
    suspend fun getAll(offset:Int, limit:Int): List<PokemonEntity>
}