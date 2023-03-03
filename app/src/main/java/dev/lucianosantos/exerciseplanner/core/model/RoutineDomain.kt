package dev.lucianosantos.exerciseplanner.core.model

data class RoutineDomain(
    val id: String,
    val name: String,
    val daysOfWeek: List<Int>
)
