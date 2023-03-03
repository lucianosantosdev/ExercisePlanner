package dev.lucianosantos.exerciseplanner.fragments.collections.model

data class ExerciseItem (
    val id: String,
    val name: String,
    val sessions: Int,
    val repetitions: Int,
)
