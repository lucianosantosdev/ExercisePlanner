package dev.lucianosantos.exerciseplanner.core.repository

import androidx.lifecycle.LiveData
import dev.lucianosantos.exerciseplanner.core.database.entity.Routine
import dev.lucianosantos.exerciseplanner.core.model.RoutineDomain
import dev.lucianosantos.exerciseplanner.fragments.collections.model.RoutineItem

interface IRoutinesRepository {

    suspend fun getById(id: String) : RoutineDomain?

    suspend fun fetchRoutines() : List<RoutineDomain>

    suspend fun addRoutine(name: String, daysOfWeek: List<Int>)
}
