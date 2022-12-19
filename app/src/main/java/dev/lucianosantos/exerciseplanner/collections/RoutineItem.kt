package dev.lucianosantos.exerciseplanner.collections

data class RoutineItem(
    val id: String,
    val name: String,
    val daysOfWeek: List<Int>
)
