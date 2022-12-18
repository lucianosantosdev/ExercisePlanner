package dev.lucianosantos.exerciseplanner.items

import java.time.DayOfWeek

data class RoutineItem(
    val name: String,
    val daysOfWeek: List<Int>
)
