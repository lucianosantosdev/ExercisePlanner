package dev.lucianosantos.exerciseplanner.repositories

import dev.lucianosantos.exerciseplanner.data.Routine
import dev.lucianosantos.exerciseplanner.data.RoutineDao
import kotlinx.coroutines.flow.Flow
import java.util.UUID

class RoutinesRepository(private val routineDao: RoutineDao) {

    suspend fun getById(id: String) = routineDao.getById(id)

    suspend fun fetchRoutines() : List<Routine> = routineDao.getAll()

    suspend fun addRoutine(name: String, daysOfWeek: List<Int>) {
        var routine = Routine(
            id = UUID.randomUUID().toString(),
            name = name,
            daysOfWeek = daysOfWeek
        )
        routineDao.insert(routine)
    }

    suspend fun getRoutineById(id: String) : Routine? = routineDao.getById(id)
}
