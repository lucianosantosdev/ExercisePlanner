package dev.lucianosantos.exerciseplanner.core.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import dev.lucianosantos.exerciseplanner.core.database.dao.ExerciseDao
import dev.lucianosantos.exerciseplanner.core.database.dao.RoutineDao
import dev.lucianosantos.exerciseplanner.core.database.entity.Exercise
import dev.lucianosantos.exerciseplanner.core.database.entity.Routine
import dev.lucianosantos.exerciseplanner.core.database.typeconverter.DaysOfWeekTypeConverter

@Database(entities = [Routine::class, Exercise::class], version = 1)
@TypeConverters(DaysOfWeekTypeConverter::class)
abstract class AppDatabase : RoomDatabase() {
    abstract fun routineDao(): RoutineDao
    abstract fun exerciseDao(): ExerciseDao

    companion object {
        // For Singleton instantiation
        @Volatile private var instance: AppDatabase? = null

        fun getInstance(context: Context): AppDatabase {
            return instance ?: synchronized(this) {
                instance ?: buildDatabase(context).also { instance = it }
            }
        }

        private fun buildDatabase(context: Context): AppDatabase {
            return Room.databaseBuilder(context, AppDatabase::class.java, "exercise-planner-db").build()
        }
    }
}