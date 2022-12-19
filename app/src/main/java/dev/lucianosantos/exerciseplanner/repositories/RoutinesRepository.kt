package dev.lucianosantos.exerciseplanner.repositories

import dev.lucianosantos.exerciseplanner.collections.ExerciseItem

interface ExercisesRepository {

    fun fetchExercises() : List<ExerciseItem>

    fun addExercise(name: String)
}