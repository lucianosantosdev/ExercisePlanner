package dev.lucianosantos.exerciseplanner.core.repository

import androidx.lifecycle.LiveData
import dev.lucianosantos.exerciseplanner.core.database.entity.Exercise
import dev.lucianosantos.exerciseplanner.core.model.ExerciseDomain

interface IExerciseRepository {

    suspend fun getById(exerciseId: String) : ExerciseDomain?

    suspend fun addExercise(exercise: ExerciseDomain)

    suspend fun fetchExercises(routineId: String? = null) : List<ExerciseDomain>
}