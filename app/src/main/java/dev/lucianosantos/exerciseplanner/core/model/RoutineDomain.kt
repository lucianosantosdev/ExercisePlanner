package dev.lucianosantos.exerciseplanner.core.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.util.*

data class RoutineDomain(
    val id: String,
    val name: String,
    val daysOfWeek: List<Int>
)
