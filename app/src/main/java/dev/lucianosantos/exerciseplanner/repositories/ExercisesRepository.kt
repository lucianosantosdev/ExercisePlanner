package dev.lucianosantos.exerciseplanner.repositories

import dev.lucianosantos.exerciseplanner.collections.RoutineItem

interface RoutinesRepository {

    fun fetchRoutines() : List<RoutineItem>

    fun addRoutine(name: String, daysOfWeek: List<Int>)
}