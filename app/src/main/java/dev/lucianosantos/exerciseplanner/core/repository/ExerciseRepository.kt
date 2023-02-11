package dev.lucianosantos.exerciseplanner.core.repository

import androidx.lifecycle.LiveData
import dev.lucianosantos.exerciseplanner.core.database.entity.Exercise
import dev.lucianosantos.exerciseplanner.core.database.dao.ExerciseDao
import javax.inject.Inject

class ExerciseRepository @Inject constructor(
    private val exerciseDao: ExerciseDao
) : IExerciseRepository {

    override suspend fun getById(exerciseId: String) = exerciseDao.getById(exerciseId)

    override suspend fun addExercise(exercise: Exercise) {
        exerciseDao.insert(exercise)
    }

    override fun fetchExercises(routineId: String?) : LiveData<List<Exercise>> =
    if (routineId == null) {
        exerciseDao.getAll()
    } else {
        exerciseDao.getAllByRoutineId(routineId)
    }
}