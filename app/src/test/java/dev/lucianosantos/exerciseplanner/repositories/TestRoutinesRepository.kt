package dev.lucianosantos.exerciseplanner.repositories

import dev.lucianosantos.exerciseplanner.collections.RoutineItem
import java.util.*

class TestRoutinesRepository : RoutinesRepository {

    val routineList = mutableListOf<RoutineItem>()

    override fun fetchRoutines(): List<RoutineItem> = routineList

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