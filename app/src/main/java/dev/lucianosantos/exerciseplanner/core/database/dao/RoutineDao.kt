package dev.lucianosantos.exerciseplanner.core.database.dao

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

    @Update(entity = Routine::class)
    fun update(obj: RoutineUpdate)

    @Entity
    data class RoutineUpdate (
        val id : String,
        val name : String,
        val daysOfWeek: List<Int>,
    )
}