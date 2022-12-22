package dev.lucianosantos.exerciseplanner.models

data class Routine(
    val id: String,
    val name: String,
    val daysOfWeek: List<Int>,
    val exercises: List<Exercise>
)
