package dev.lucianosantos.exerciseplanner.core.repository

import dev.lucianosantos.exerciseplanner.core.model.RoutineDomain

interface IRoutinesRepository {

    suspend fun getById(id: String) : RoutineDomain?

    suspend fun fetchRoutines() : List<RoutineDomain>

    suspend fun addRoutine(name: String, daysOfWeek: List<Int>) : String

    suspend fun updateRoutine(id: String, name: String, daysOfWeek: List<Int>)
}
