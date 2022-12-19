package dev.lucianosantos.exerciseplanner.dummy

import dev.lucianosantos.exerciseplanner.collections.RoutineItem
import dev.lucianosantos.exerciseplanner.repositories.RoutinesRepository
import java.util.UUID

object MockRoutines : RoutinesRepository  {
    val routineList: MutableList<RoutineItem> = mutableListOf(
        RoutineItem(
            id = UUID.randomUUID().toString(),
            name = "Canoagem",
            daysOfWeek = listOf(1,2,3,4,5,6,7)
        ),
        RoutineItem(
            id = UUID.randomUUID().toString(),
            name = "BJJ",
            daysOfWeek = listOf(1,2,3,4,5,6,7)
        ),
        RoutineItem(
            id = UUID.randomUUID().toString(),
            name = "Natação",
            daysOfWeek = listOf(1,2,3,4,5,6,7)
        ),
        RoutineItem(
            id = UUID.randomUUID().toString(),
            name = "Tai Chi",
            daysOfWeek = listOf(1,2,3,4,5,6,7)
        ),
    )

    override fun fetchRoutines(): List<RoutineItem> {
        return routineList.map { it.copy() }
    }

    override fun addRoutine(name: String, daysOfWeek: List<Int>) {
        routineList.add(
            RoutineItem(
                id = UUID.randomUUID().toString(),
                name = name,
                daysOfWeek = daysOfWeek
            )
        )
    }
}