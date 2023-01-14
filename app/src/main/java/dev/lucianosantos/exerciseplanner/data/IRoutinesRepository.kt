package dev.lucianosantos.exerciseplanner.repositories

import androidx.lifecycle.LiveData
import dev.lucianosantos.exerciseplanner.data.Routine

interface IRoutinesRepository {

    suspend fun getById(id: String) : Routine?

    fun fetchRoutines() : LiveData<List<Routine>>

    suspend fun addRoutine(name: String, daysOfWeek: List<Int>)
}
