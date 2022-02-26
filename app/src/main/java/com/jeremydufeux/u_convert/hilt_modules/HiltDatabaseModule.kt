package com.jeremydufeux.u_convert.hilt_modules

import android.content.Context
import androidx.room.Room
import com.jeremydufeux.u_convert.database.AppDatabase
import com.jeremydufeux.u_convert.models.database_entities.UniverseDao
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
class HiltDatabaseModule {

    @Singleton
    @Provides
    fun provideDatabase(@ApplicationContext context: Context): AppDatabase {
        return Room.databaseBuilder(
            context,
            AppDatabase::class.java,
            "U_Convert.db")
            .fallbackToDestructiveMigration()
            .build()
    }

    @Provides
    fun provideUniverseDao(database: AppDatabase): UniverseDao {
        return database.universeDao()
    }
}