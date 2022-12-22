package dev.lucianosantos.exerciseplanner.repositories

import dev.lucianosantos.exerciseplanner.models.Exercise

interface ExercisesRepository {

    fun fetchExercises() : List<Exercise>

    fun addExercise(name: String)
}