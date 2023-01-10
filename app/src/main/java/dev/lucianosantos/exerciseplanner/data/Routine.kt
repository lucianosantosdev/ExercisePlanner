package dev.lucianosantos.exerciseplanner.data

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.util.UUID

@Entity
data class Routine(
    @PrimaryKey val id: String,
    val name: String,
    val daysOfWeek: List<Int>
) {
    constructor() : this(UUID.randomUUID().toString(), "", listOf())
}
