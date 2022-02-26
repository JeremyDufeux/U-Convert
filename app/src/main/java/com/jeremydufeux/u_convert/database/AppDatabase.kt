package com.jeremydufeux.u_convert.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.jeremydufeux.u_convert.models.database_entities.UniverseDao
import com.jeremydufeux.u_convert.models.database_entities.UniverseEntity

@Database(
    entities = [UniverseEntity::class],
    version = 1,
    exportSchema = true
)
abstract class AppDatabase : RoomDatabase(){
    abstract fun universeDao() : UniverseDao
}