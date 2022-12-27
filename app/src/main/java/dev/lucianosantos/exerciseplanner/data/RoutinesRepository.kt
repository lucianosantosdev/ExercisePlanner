package dev.lucianosantos.exerciseplanner.repositories

import dev.lucianosantos.exerciseplanner.data.Exercise

interface ExercisesRepository {

    fun fetchExercises() : List<Exercise>

    fun addExercise(name: String)

    fun fetchExercisesByRoutineId(routineId: String): List<Exercise>
}