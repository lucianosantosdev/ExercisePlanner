package dev.lucianosantos.exerciseplanner.fragments.collections.model

import dev.lucianosantos.exerciseplanner.core.model.ExerciseDomain

data class ExerciseItem (
    val id: String,
    val name: String,
    val sessions: Int,
    val repetitions: Int,
)
