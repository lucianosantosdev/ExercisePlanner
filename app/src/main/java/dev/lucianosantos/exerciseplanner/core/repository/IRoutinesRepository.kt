package dev.lucianosantos.exerciseplanner.core.repository

import androidx.lifecycle.LiveData
import dev.lucianosantos.exerciseplanner.core.database.entity.Routine

interface IRoutinesRepository {

    suspend fun getById(id: String) : Routine?

    fun fetchRoutines() : LiveData<List<Routine>>

    suspend fun addRoutine(name: String, daysOfWeek: List<Int>)
}
