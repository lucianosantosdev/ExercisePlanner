package dev.lucianosantos.exerciseplanner.core.repository

import dev.lucianosantos.exerciseplanner.core.database.dao.ExerciseDao
import dev.lucianosantos.exerciseplanner.core.model.ExerciseDomain
import dev.lucianosantos.exerciseplanner.core.model.toDomain
import dev.lucianosantos.exerciseplanner.core.model.toEntity
import javax.inject.Inject

class ExerciseRepository @Inject constructor(
    private val exerciseDao: ExerciseDao
) : IExerciseRepository {

    override suspend fun getById(exerciseId: String) = exerciseDao.getById(exerciseId)?.toDomain()

    override suspend fun addExercise(exercise: ExerciseDomain) {
        exerciseDao.insert(exercise.toEntity())
    }

    override suspend fun fetchExercises(routineId: String?) : List<ExerciseDomain> {
        val exerciseEntityList = if (routineId == null) {
            exerciseDao.getAll()
        } else {
            exerciseDao.getAllByRoutineId(routineId)
        }
        return exerciseEntityList.map { it.toDomain() }
    }
}