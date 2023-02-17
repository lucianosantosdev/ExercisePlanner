package dev.lucianosantos.exerciseplanner.core.model

import dev.lucianosantos.exerciseplanner.core.database.entity.Exercise

fun Exercise.toDomain() = ExerciseDomain(
    id =  this.id,
    routineId = this.routineId,
    name = this.name,
    sessions = this.sessions,
    repetitions = this.repetitions,
    timeSeconds = this.timeSeconds,
    intensity = this.intensity,
    distance = this.distance,
    weight = this.weight,
)

fun ExerciseDomain.toEntity() = Exercise(
    id =  this.id,
    routineId = this.routineId,
    name = this.name,
    sessions = this.sessions,
    repetitions = this.repetitions,
    timeSeconds = this.timeSeconds,
    intensity = this.intensity,
    distance = this.distance,
    weight = this.weight,
)