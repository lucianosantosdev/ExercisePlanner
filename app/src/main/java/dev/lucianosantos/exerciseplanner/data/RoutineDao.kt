package dev.lucianosantos.exerciseplanner.data

import androidx.room.*
import kotlinx.coroutines.flow.Flow

@Dao
interface RoutineDao {
    @Insert
    suspend fun insert(routine: Routine)

//    @Delete
//    suspend fun delete(routine: Routine)

    @Query("SELECT * FROM routine")
    suspend fun getAll() : List<Routine>

    @Query("SELECT * FROM routine WHERE id = :id")
    suspend fun getById(id: String) : Routine?

//    @Transaction
//    @Query("SELECT * FROM routine")
//    suspend fun getAllRoutineWithExercises(): List<RoutineWithExercises>
//
//    @Transaction
//    @Query("SELECT * FROM routine WHERE id = :id")
//    suspend fun getRoutineWithExercisesById(id: String): List<RoutineWithExercises>
}