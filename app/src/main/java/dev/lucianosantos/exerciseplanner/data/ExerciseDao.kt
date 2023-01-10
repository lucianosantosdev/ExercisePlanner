package dev.lucianosantos.exerciseplanner.data

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query

@Dao
interface ExerciseDao {
    @Insert
    suspend fun insert(exercise: Exercise)

    @Delete
    suspend fun delete(exercise: Exercise)

    @Query("SELECT * FROM exercise")
    fun getAll() : LiveData<List<Exercise>>

    @Query("SELECT * FROM exercise WHERE routineId = :routineId")
    fun getAllByRoutineId(routineId: String) : LiveData<List<Exercise>>

    @Query("SELECT * FROM exercise WHERE id = :exerciseId")
    suspend fun getById(exerciseId: String) : Exercise?



}