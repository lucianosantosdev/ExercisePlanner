package dev.lucianosantos.exerciseplanner.models

import kotlin.time.Duration

data class Exercise(
    val id: String,
    val name: String,
    val sessions: Int?,
    val repetitions: Int?,
    val duration: Duration?,
    val intensity: Int?,
    val weight: Int?
)
