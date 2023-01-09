package dev.lucianosantos.exerciseplanner.data

import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlin.time.Duration

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
) {
    constructor(id: String, name: String, routineId: String) : this(id, routineId, name, 0,0,0,0,0,0) {}
}
