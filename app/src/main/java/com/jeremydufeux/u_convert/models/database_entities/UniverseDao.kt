package com.jeremydufeux.u_convert.models.database_entities

import androidx.room.*
import androidx.room.OnConflictStrategy.REPLACE

@Dao
interface UniverseDao {

    @Query("SELECT * FROM universes")
    suspend fun getUniverses(): List<UniverseEntity>

    @Insert(onConflict = REPLACE)
    suspend fun insertUniverse(universe: UniverseEntity)

    @Update
    suspend fun updateUniverse(universe: UniverseEntity)

    @Delete
    suspend fun deleteUniverse(universe: UniverseEntity)
}