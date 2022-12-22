package dev.lucianosantos.exerciseplanner.repositories

import dev.lucianosantos.exerciseplanner.models.Routine
import java.util.*

class TestRoutinesRepository : RoutinesRepository {

    val routineList = mutableListOf<Routine>()

    override fun fetchRoutines(): List<Routine> = routineList

    override fun addRoutine(name: String, daysOfWeek: List<Int>) {
        routineList.add(
            Routine(
                id = UUID.randomUUID().toString(),
                name = name,
                daysOfWeek = daysOfWeek
            )
        )
    }
}