package dev.lucianosantos.exerciseplanner.core.database.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Routine(
    @PrimaryKey val id: String,
    val name: String,
    val daysOfWeek: List<Int>
)