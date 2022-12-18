package dev.lucianosantos.exerciseplanner.dummy

import dev.lucianosantos.exerciseplanner.items.RoutineItem
import java.time.DayOfWeek

object MockRoutineItem {
    val routineList: MutableList<RoutineItem> = mutableListOf(
        RoutineItem(
            name = "Canoagem",
            daysOfWeek = listOf(1,2,3,4,5,6,7)
        ),
        RoutineItem(
            name = "BJJ",
            daysOfWeek = listOf(1,2,3,4,5,6,7)
        ),
        RoutineItem(
            name = "Natação",
            daysOfWeek = listOf(1,2,3,4,5,6,7)
        ),
        RoutineItem(
            name = "Tai Chi",
            daysOfWeek = listOf(1,2,3,4,5,6,7)
        ),
    )
}