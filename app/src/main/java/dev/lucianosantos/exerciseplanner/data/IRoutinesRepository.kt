package dev.lucianosantos.exerciseplanner.repositories

import androidx.lifecycle.LiveData
import dev.lucianosantos.exerciseplanner.data.Routine
import dev.lucianosantos.exerciseplanner.data.RoutineDao
import kotlinx.coroutines.flow.Flow
import java.util.UUID

interface IRoutinesRepository {

    suspend fun getById(id: String) : Routine?

    fun fetchRoutines() : LiveData<List<Routine>>

    suspend fun addRoutine(name: String, daysOfWeek: List<Int>)
}
