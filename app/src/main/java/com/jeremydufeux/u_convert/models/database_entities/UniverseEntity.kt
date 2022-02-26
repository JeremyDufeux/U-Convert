package com.jeremydufeux.u_convert.models.database_entities

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "universes")
data class UniverseEntity(
    @PrimaryKey(autoGenerate = true)
    val id : Int? = null,
    val absoluteUniverse : Int,
    val amount : Int = 8,
    val label : String
)
