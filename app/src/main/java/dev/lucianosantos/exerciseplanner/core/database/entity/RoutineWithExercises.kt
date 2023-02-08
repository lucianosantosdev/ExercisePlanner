package dev.lucianosantos.exerciseplanner.core.database.entity

import androidx.room.Embedded
import androidx.room.Relation

/**
 * This class captures the one-to-many relationship between a [Routine] and [Exercise].
 */
data class RoutineWithExercises(
    @Embedded val routine: Routine,
    @Relation(
        parentColumn = "id",
        entityColumn = "id"
    )
    val exercises: List<Exercise>
)