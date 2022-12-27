package dev.lucianosantos.exerciseplanner.data

import androidx.room.*
import kotlinx.coroutines.flow.Flow

@Dao
interface RoutineDao {
    @Insert
    suspend fun insert(routine: Routine)

    @Delete
    suspend fun delete(routine: Routine)

    @Query("SELECT * FROM routine")
    fun getAll() : Flow<List<Routine>>

    @Query("SELECT * FROM routine WHERE id = :id")
    fun getById(id: String) : Routine?

    @Transaction
    @Query("SELECT * FROM routine")
    fun getAllRoutineWithExercises(): List<RoutineWithExercises>

    @Transaction
    @Query("SELECT * FROM routine WHERE id = :id")
    fun getRoutineWithExercisesById(id: String): List<RoutineWithExercises>
}