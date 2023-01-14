package dev.lucianosantos.exerciseplanner.repositories

import androidx.lifecycle.LiveData
import dev.lucianosantos.exerciseplanner.data.Exercise

interface IExercisesRepository {

    suspend fun getById(exerciseId: String) : Exercise?

    suspend fun addExercise(exercise: Exercise)

    fun fetchExercises(routineId: String? = null) : LiveData<List<Exercise>>
}