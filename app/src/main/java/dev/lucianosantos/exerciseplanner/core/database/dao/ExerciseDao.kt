package dev.lucianosantos.exerciseplanner.core.database.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import dev.lucianosantos.exerciseplanner.core.database.entity.Exercise

@Dao
interface ExerciseDao {
    @Insert
    suspend fun insert(exercise: Exercise)

    @Delete
    suspend fun delete(exercise: Exercise)

    @Query("SELECT * FROM exercise")
    suspend fun getAll() : List<Exercise>

    @Query("SELECT * FROM exercise WHERE routineId = :routineId")
    suspend fun getAllByRoutineId(routineId: String) : List<Exercise>

    @Query("SELECT * FROM exercise WHERE id = :exerciseId")
    suspend fun getById(exerciseId: String) : Exercise?



}