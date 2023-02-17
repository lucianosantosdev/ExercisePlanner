package dev.lucianosantos.exerciseplanner.core.database.dao

import androidx.lifecycle.LiveData
import androidx.room.*
import dev.lucianosantos.exerciseplanner.core.database.entity.Routine

@Dao
interface RoutineDao {
    @Insert
    suspend fun insert(routine: Routine)

    @Delete
    suspend fun delete(routine: Routine)

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