package com.eferraz.projecttest.backend.datasources.local

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class PokemonEntity(
    @PrimaryKey val id: Long,
    val name: String,
    val json: String
)