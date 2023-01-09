package dev.lucianosantos.exerciseplanner.repositories

import dev.lucianosantos.exerciseplanner.data.Exercise
import dev.lucianosantos.exerciseplanner.data.ExerciseDao
import dev.lucianosantos.exerciseplanner.data.Routine
import java.util.*

class ExercisesRepository(private val exercisesDao: ExerciseDao) {

    suspend fun getById(exerciseId: String) = exercisesDao.getById(exerciseId)

    suspend fun addExercise(name: String, routineId: String) {
        var exercise = Exercise(
            id = UUID.randomUUID().toString(),
            name = name,
            routineId = routineId
        )
        exercisesDao.insert(exercise)
    }

    suspend fun fetchExercises(routineId: String? = null) =
    if (routineId == null) {
        exercisesDao.getAll()
    } else {
        exercisesDao.getAllByRoutineId(routineId)
    }
}