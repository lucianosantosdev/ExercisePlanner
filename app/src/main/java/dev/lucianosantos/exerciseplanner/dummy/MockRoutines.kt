package dev.lucianosantos.exerciseplanner.dummy

import dev.lucianosantos.exerciseplanner.models.Routine
import dev.lucianosantos.exerciseplanner.repositories.RoutinesRepository
import java.util.*

object MockRoutines : RoutinesRepository  {
    val routineList: MutableList<Routine> = mutableListOf()

    override fun fetchRoutines(): List<Routine> {
        return routineList.map { it.copy() }
    }

    override fun addRoutine(name: String, daysOfWeek: List<Int>) {
        routineList.add(
            Routine(
                id = UUID.randomUUID().toString(),
                name = name,
                daysOfWeek = daysOfWeek,
                listOf()
            )
        )
    }
}