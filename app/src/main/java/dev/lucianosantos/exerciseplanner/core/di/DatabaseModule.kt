package dev.lucianosantos.exerciseplanner.core.di

import android.app.Application
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import dev.lucianosantos.exerciseplanner.core.database.AppDatabase
import dev.lucianosantos.exerciseplanner.core.database.dao.ExerciseDao
import dev.lucianosantos.exerciseplanner.core.database.dao.RoutineDao
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
class DatabaseModule {
    @Singleton
    @Provides
    fun providesRoomDatabase(application: Application) : AppDatabase {
        return AppDatabase.getInstance(application)
    }

    @Singleton
    @Provides
    fun providesExerciseDao(database: AppDatabase) : ExerciseDao {
        return database.exerciseDao()
    }

    @Singleton
    @Provides
    fun providesRoutineDao(database: AppDatabase) : RoutineDao {
        return database.routineDao()
    }
}