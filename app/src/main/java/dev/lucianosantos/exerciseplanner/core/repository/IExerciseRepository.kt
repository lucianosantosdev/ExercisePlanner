package dev.lucianosantos.exerciseplanner.core.repository

import dev.lucianosantos.exerciseplanner.core.model.ExerciseDomain

interface IExerciseRepository {

    suspend fun getById(exerciseId: String) : ExerciseDomain?

    suspend fun addExercise(exercise: ExerciseDomain)

    suspend fun fetchExercises(routineId: String? = null) : List<ExerciseDomain>
}