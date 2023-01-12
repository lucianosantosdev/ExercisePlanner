package dev.lucianosantos.exerciseplanner.repositories

import androidx.lifecycle.LiveData
import androidx.lifecycle.LiveDataReactiveStreams
import dev.lucianosantos.exerciseplanner.data.Exercise
import dev.lucianosantos.exerciseplanner.data.ExerciseDao
import dev.lucianosantos.exerciseplanner.data.Routine
import java.util.*

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