package dev.lucianosantos.exerciseplanner.core.model


data class ExerciseDomain(
    val id: String,
    val routineId: String,
    val name: String,
    val sessions: Int?,
    val repetitions: Int?,
    val timeSeconds: Int?,
    val intensity: Int?,
    val distance: Int?,
    val weight: Int?
)