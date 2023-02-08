package dev.lucianosantos.exerciseplanner.core.repository

import androidx.lifecycle.LiveData
import dev.lucianosantos.exerciseplanner.core.database.entity.Exercise

interface IExercisesRepository {

    suspend fun getById(exerciseId: String) : Exercise?

    suspend fun addExercise(exercise: Exercise)

    fun fetchExercises(routineId: String? = null) : LiveData<List<Exercise>>
}