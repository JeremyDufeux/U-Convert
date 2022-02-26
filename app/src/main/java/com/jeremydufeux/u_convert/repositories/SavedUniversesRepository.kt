package com.jeremydufeux.u_convert.repositories

import com.jeremydufeux.u_convert.models.Universe
import com.jeremydufeux.u_convert.models.database_entities.UniverseDao
import com.jeremydufeux.u_convert.models.database_entities.UniverseEntity
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class SavedUniversesRepository @Inject constructor(
    private val universeDao: UniverseDao
) {
    suspend fun saveUniverse(universe: Universe) {
        universeDao.insertUniverse(universe.toUniverseEntity())
    }

    private fun Universe.toUniverseEntity() : UniverseEntity {
        return UniverseEntity(
            absoluteUniverse = decimalUniverse0,
            label = label
        )
    }

}