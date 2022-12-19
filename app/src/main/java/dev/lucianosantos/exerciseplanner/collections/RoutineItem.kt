package dev.lucianosantos.exerciseplanner.collections

import java.time.DayOfWeek

data class RoutineItem(
    val id: String,
    val name: String,
    val daysOfWeek: List<Int>
)
