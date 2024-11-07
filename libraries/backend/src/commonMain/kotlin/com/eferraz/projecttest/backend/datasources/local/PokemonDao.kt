package com.eferraz.projecttest.backend.datasources.local

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface PokemonDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(vararg pokemon: PokemonEntity)

    @Query("SELECT * FROM PokemonEntity WHERE id = :id")
    suspend fun get(id: Long): PokemonEntity?
}