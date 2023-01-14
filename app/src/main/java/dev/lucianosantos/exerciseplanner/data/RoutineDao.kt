package dev.lucianosantos.exerciseplanner.data

import androidx.lifecycle.LiveData
import androidx.room.*

@Dao
interface RoutineDao {
    @Insert
    suspend fun insert(routine: Routine)

    @Delete
    suspend fun delete(routine: Routine)

    @Query("SELECT * FROM routine")
    fun getAll() : LiveData<List<Routine>>

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