package dev.lucianosantos.exerciseplanner.core.di

import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import dev.lucianosantos.exerciseplanner.core.repository.ExerciseRepository
import dev.lucianosantos.exerciseplanner.core.repository.IExerciseRepository
import dev.lucianosantos.exerciseplanner.core.repository.IRoutinesRepository
import dev.lucianosantos.exerciseplanner.core.repository.RoutinesRepository
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
abstract class RepositoryModule {
    @Singleton
    @Binds
    abstract fun bindExerciseRepository(impl: ExerciseRepository) : IExerciseRepository

    @Singleton
    @Binds
    abstract fun bindRoutineRepository(impl: RoutinesRepository) : IRoutinesRepository
}