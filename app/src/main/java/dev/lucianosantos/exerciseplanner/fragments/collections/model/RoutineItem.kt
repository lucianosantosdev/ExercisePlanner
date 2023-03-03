package dev.lucianosantos.exerciseplanner.fragments.collections.model

import dev.lucianosantos.exerciseplanner.core.model.RoutineDomain

data class RoutineItem (
    val id: String,
    val name: String,
    val daysOfWeek: List<Int>
)

fun RoutineDomain.toRoutineItem() = RoutineItem(
    id = this.id,
    name = this.name,
    daysOfWeek = this.daysOfWeek
)