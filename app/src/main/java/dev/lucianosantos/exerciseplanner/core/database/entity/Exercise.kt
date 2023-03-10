package dev.lucianosantos.exerciseplanner.core.database.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Exercise(
    @PrimaryKey val id: String,
    val routineId: String,
    val name: String,
    val sessions: Int?,
    val repetitions: Int?,
    val timeSeconds: Int?,
    val intensity: Int?,
    val distance: Int?,
    val weight: Int?
)