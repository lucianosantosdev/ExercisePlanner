package dev.lucianosantos.exerciseplanner.dummy

import dev.lucianosantos.exerciseplanner.models.Exercise
import dev.lucianosantos.exerciseplanner.repositories.ExercisesRepository
import java.util.*
import kotlin.time.Duration

object MockExercises : ExercisesRepository {
    private val exerciseList: MutableList<Exercise> = mutableListOf()

    override fun fetchExercises(): List<Exercise> {
        return exerciseList.map { it.copy() }
    }

    override fun addExercise(name: String) {
        exerciseList.add(
            Exercise(
                id = UUID.randomUUID().toString(),
                name = "Test Exercise",
                sessions = 1,
                repetitions = 1,
                duration = Duration.ZERO,
                intensity = 10,
                weight = 10
            )
        )
    }
}