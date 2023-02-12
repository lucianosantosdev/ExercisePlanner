package dev.lucianosantos.exerciseplanner.core.model

import dev.lucianosantos.exerciseplanner.core.database.entity.Exercise
import dev.lucianosantos.exerciseplanner.core.database.entity.Routine

fun Routine.toDomain() = RoutineDomain(
    id =  this.id,
    name = this.name,
    daysOfWeek = this.daysOfWeek
)

fun RoutineDomain.toEntity() = Routine(
    id =  this.id,
    name = this.name,
    daysOfWeek = this.daysOfWeek
)