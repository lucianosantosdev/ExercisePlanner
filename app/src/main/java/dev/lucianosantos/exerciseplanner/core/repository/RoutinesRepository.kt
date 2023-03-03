package dev.lucianosantos.exerciseplanner.core.repository

import dev.lucianosantos.exerciseplanner.core.database.dao.RoutineDao
import dev.lucianosantos.exerciseplanner.core.database.entity.Routine
import dev.lucianosantos.exerciseplanner.core.model.toDomain
import java.util.*
import javax.inject.Inject

class RoutinesRepository @Inject constructor(
    private val routineDao: RoutineDao
) : IRoutinesRepository {

    override suspend fun getById(id: String) = routineDao.getById(id)?.toDomain()

    override suspend fun fetchRoutines() = routineDao.getAll().map { it.toDomain() }

    override suspend fun addRoutine(name: String, daysOfWeek: List<Int>) {
        var routine = Routine(
            id = UUID.randomUUID().toString(),
            name = name,
            daysOfWeek = daysOfWeek
        )
        routineDao.insert(routine)
    }

    override suspend fun updateRoutine(id: String, name: String, daysOfWeek: List<Int>) {
        routineDao.update(RoutineDao.RoutineUpdate(
            id = id,
            name = name, daysOfWeek = daysOfWeek)
        )
    }
}
