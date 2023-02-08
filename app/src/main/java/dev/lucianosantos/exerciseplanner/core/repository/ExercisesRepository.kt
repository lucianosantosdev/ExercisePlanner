package dev.lucianosantos.exerciseplanner.core.repository

import androidx.lifecycle.LiveData
import dev.lucianosantos.exerciseplanner.core.database.entity.Exercise
import dev.lucianosantos.exerciseplanner.core.database.dao.ExerciseDao

class ExercisesRepository(private val exercisesDao: ExerciseDao) : IExercisesRepository {

    override suspend fun getById(exerciseId: String) = exercisesDao.getById(exerciseId)

    override suspend fun addExercise(exercise: Exercise) {
        exercisesDao.insert(exercise)
    }

    override fun fetchExercises(routineId: String?) : LiveData<List<Exercise>> =
    if (routineId == null) {
        exercisesDao.getAll()
    } else {
        exercisesDao.getAllByRoutineId(routineId)
    }
}