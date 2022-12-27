package dev.lucianosantos.exerciseplanner.data

import androidx.room.TypeConverter
import androidx.room.TypeConverters
import kotlin.time.Duration

class Converters {

    @TypeConverter
    fun fromIntList(value: List<Int>): String = value.joinToString(separator = ",")

    @TypeConverter
    fun stringToIntList(string: String): List<Int> = string.split(",").map { it.toInt() }
}