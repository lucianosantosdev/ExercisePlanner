package dev.lucianosantos.exerciseplanner.repositories

import dev.lucianosantos.exerciseplanner.models.Routine

interface RoutinesRepository {

    fun fetchRoutines() : List<Routine>

    fun addRoutine(name: String, daysOfWeek: List<Int>)

    fun getRoutineById(id: String) : Routine
}