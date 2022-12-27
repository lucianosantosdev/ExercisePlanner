package dev.lucianosantos.exerciseplanner.data

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Routine(
    @PrimaryKey val id: String,
    val name: String,
    val daysOfWeek: List<Int>,
//    val exercises: List<Exercise>
)
