package dev.lucianosantos.exerciseplanner.repositories

import androidx.lifecycle.LiveData
import androidx.lifecycle.LiveDataReactiveStreams
import dev.lucianosantos.exerciseplanner.data.Exercise
import dev.lucianosantos.exerciseplanner.data.ExerciseDao
import dev.lucianosantos.exerciseplanner.data.Routine
import java.util.*

interface IExercisesRepository {

    suspend fun getById(exerciseId: String) : Exercise?

    suspend fun addExercise(exercise: Exercise)

    fun fetchExercises(routineId: String? = null) : LiveData<List<Exercise>>
}