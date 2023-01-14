package dev.lucianosantos.exerciseplanner.repositories

import dev.lucianosantos.exerciseplanner.data.Routine
import dev.lucianosantos.exerciseplanner.data.RoutineDao
import java.util.*

class RoutinesRepository(private val routineDao: RoutineDao) : IRoutinesRepository {

    override suspend fun getById(id: String) = routineDao.getById(id)

    override fun fetchRoutines() = routineDao.getAll()

    override suspend fun addRoutine(name: String, daysOfWeek: List<Int>) {
        var routine = Routine(
            id = UUID.randomUUID().toString(),
            name = name,
            daysOfWeek = daysOfWeek
        )
        routineDao.insert(routine)
    }
}
