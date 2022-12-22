package dev.lucianosantos.exerciseplanner.dummy

import dev.lucianosantos.exerciseplanner.models.Routine
import dev.lucianosantos.exerciseplanner.repositories.RoutinesRepository
import java.util.*

object MockRoutines : RoutinesRepository  {
    val routineList: MutableList<Routine> = mutableListOf(
        Routine(
            id = UUID.randomUUID().toString(),
            name = "Canoagem",
            daysOfWeek = listOf(1,2,3,4,5,6,7),
            exercises = MockExercises.fetchExercises()
        ),
        Routine(
            id = UUID.randomUUID().toString(),
            name = "BJJ",
            daysOfWeek = listOf(1,2,3,4,5,6,7),
            exercises = MockExercises.fetchExercises()
        ),
        Routine(
            id = UUID.randomUUID().toString(),
            name = "Natação",
            daysOfWeek = listOf(1,2,3,4,5,6,7),
            exercises = MockExercises.fetchExercises()
        ),
        Routine(
            id = UUID.randomUUID().toString(),
            name = "Tai Chi",
            daysOfWeek = listOf(1,2,3,4,5,6,7),
            exercises = MockExercises.fetchExercises()
        ),
    )

    override fun fetchRoutines(): List<Routine> {
        return routineList.map { it.copy() }
    }

    override fun addRoutine(name: String, daysOfWeek: List<Int>) {
        routineList.add(
            Routine(
                id = UUID.randomUUID().toString(),
                name = name,
                daysOfWeek = daysOfWeek,
                exercises = MockExercises.fetchExercises()
            )
        )
    }

    override fun getRoutineById(id: String) = routineList.first { it.id == id }
}