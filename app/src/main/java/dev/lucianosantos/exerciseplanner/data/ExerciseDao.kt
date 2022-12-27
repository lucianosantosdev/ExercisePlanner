package dev.lucianosantos.exerciseplanner.data

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query

@Dao
interface ExerciseDao {
    @Insert
    fun insert(exercise: Exercise)

    @Delete
    fun delete(exercise: Exercise)

    @Query("SELECT * FROM exercise WHERE id = :routineId")
    fun getAllByRoutineId(routineId: String) : List<Exercise>
}