package dev.lucianosantos.exerciseplanner.repositories

import dev.lucianosantos.exerciseplanner.data.Routine
import dev.lucianosantos.exerciseplanner.data.RoutineDao
import java.util.UUID

class RoutinesRepository(private val routineDao: RoutineDao) {

    fun fetchRoutines() : List<Routine> = routineDao.getAll()

    fun addRoutine(name: String, daysOfWeek: List<Int>) {
        var routine = Routine(
            id = UUID.randomUUID().toString(),
            name = name,
            daysOfWeek = daysOfWeek
        )
        routineDao.insert(routine)
    }

    fun getRoutineById(id: String) : Routine = routineDao.getById(id)
    }
}