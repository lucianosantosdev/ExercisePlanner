package dev.lucianosantos.exerciseplanner.core.repository

import dev.lucianosantos.exerciseplanner.core.database.entity.Routine
import dev.lucianosantos.exerciseplanner.core.database.dao.RoutineDao
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
