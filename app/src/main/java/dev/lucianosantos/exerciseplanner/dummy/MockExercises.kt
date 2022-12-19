package dev.lucianosantos.exerciseplanner.dummy

import dev.lucianosantos.exerciseplanner.collections.ExerciseItem
import dev.lucianosantos.exerciseplanner.repositories.ExercisesRepository
import java.util.*

object MockExercises : ExercisesRepository  {
    private val exerciseList: MutableList<ExerciseItem> = mutableListOf(
        ExerciseItem(
            id = UUID.randomUUID().toString(),
            name = "Pullup"
        ),
        ExerciseItem(
            id = UUID.randomUUID().toString(),
            name = "Flexão"
        ),
    )

    override fun fetchExercises(): List<ExerciseItem> {
        return exerciseList.map { it.copy() }
    }

    override fun addExercise(name: String) {
        exerciseList.add(
            ExerciseItem(
                id = UUID.randomUUID().toString(),
                name = name,
            )
        )
    }
}